package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.*;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhFenweiTagService;
import top.longmarch.wx.service.IGzhUserService;
import top.longmarch.wx.service.impl.SyncLock;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "解析用户标签", tags = "解析用户标签")
@RestController
@RequestMapping("/wx/gzh-user")
public class AnalyseGzhUserTagController {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserService gzhUserService;
    @Autowired
    private IGzhFenweiTagService gzhFenweiTagService;
    @Autowired
    private SyncLock syncLock;

    private static final String OPENID_URL = "http://api.unidt.com/api/fractal/openid/report?app_id=%s&app_key=%s&fee_type=%s";


    @ApiOperation(value = "解析用户标签")
    @GetMapping("/analyseUserTag")
    public Result analyseUserTag() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }

        if (StrUtil.isBlank(gzhAccount.getFwAppid()) || StrUtil.isBlank(gzhAccount.getFwAppsecret())) {
            return Result.fail("分维OpenID授权密钥错误");
        }

        String lock = "analyse_lock_" + gzhAccount.getId() + "_" + gzhAccount.getCreateBy();
        if (!syncLock.lock(lock)) {
            return Result.fail("正在解析中，请稍等...");
        }

        List<GzhUser> gzhUserList = gzhUserService.list();
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                batchAnalyseGzhUserTag(gzhUserList, gzhAccount);
            }
        });
        return Result.ok().add(gzhUserList.size());
    }

    private void batchAnalyseGzhUserTag(List<GzhUser> gzhUserList, GzhAccount gzhAccount) {
        for (GzhUser gzhUser : gzhUserList) {
            analyseGzhUserTag(gzhUser, gzhAccount);
        }
        String lock = "analyse_lock_" + gzhAccount.getId() + "_" + gzhAccount.getCreateBy();
        syncLock.unlock(lock);
    }

    private void analyseGzhUserTag(GzhUser user, GzhAccount gzhAccount) {
        String url = String.format(OPENID_URL, gzhAccount.getFwAppid(), gzhAccount.getFwAppsecret(), gzhAccount.getFeeType());
        System.out.println(url);
        JSONObject requestBody = new JSONObject();
        requestBody.put("image_url", user.getHeadImgUrl());
        requestBody.put("nickname", user.getNickname());
        requestBody.put("sex", user.getSex());
        requestBody.put("city", user.getCity());
        requestBody.put("province", user.getProvince());
        requestBody.put("country", user.getCountry());
        requestBody.put("url_used", "1");
        requestBody.put("image", "base64");
        requestBody.put("field", gzhAccount.getFwField());

        String post = HttpUtil.post(url, requestBody.toString());
        System.out.println(post);
        FwTagDTO fwTagDTO = JSONUtil.toBean(post, FwTagDTO.class);
        if (fwTagDTO.getCode() == 200) {
            saveFwTag(fwTagDTO.getResult(), user, gzhAccount.getFwField());
        }
    }

    private void saveFwTag(List<FwTag> fwTagList, GzhUser user, Integer fwField) {
        List<GzhFenweiTag> gzhFenweiTagList = fwTagList.stream().map(fwTag -> {
            GzhFenweiTag gzhFenweiTag = new GzhFenweiTag();
            BeanUtils.copyProperties(fwTag, gzhFenweiTag);
            gzhFenweiTag.setGzhId(user.getGzhId());
            gzhFenweiTag.setOpenId(user.getOpenId());
            gzhFenweiTag.setFieldId(fwField);
            return gzhFenweiTag;
        }).collect(Collectors.toList());

        gzhFenweiTagService.remove(new LambdaQueryWrapper<GzhFenweiTag>()
                .eq(GzhFenweiTag::getGzhId, user.getGzhId())
                .eq(GzhFenweiTag::getOpenId, user.getOpenId())
                .eq(GzhFenweiTag::getCreateBy, UserUtil.getUserId())
                .eq(GzhFenweiTag::getFieldId, fwField));
        gzhFenweiTagService.saveBatch(gzhFenweiTagList);
    }

}
