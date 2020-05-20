package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.*;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhFenweiTagService;
import top.longmarch.wx.service.IGzhUserService;
import top.longmarch.wx.service.impl.SyncLock;

import java.util.Arrays;
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

    @ApiOperation(value = "选择解析用户标签")
    @RequiresPermissions("wx:gzhuser:analyse")
    @PostMapping("/analyseMore")
    public Result analyseMore(@RequestBody Long[] ids) {
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        List<GzhUser> gzhUsers = gzhUserService.listByIds(Arrays.asList(ids));
        String lock = "analyse_lock_" + gzhAccount.getId() + "_" + gzhAccount.getCreateBy();
        if (!syncLock.lock(lock)) {
            return Result.fail("正在解析中，请稍等...");
        }
        batchAnalyseGzhUserTag(gzhUsers, gzhAccount, lock);
        return Result.ok().add(gzhUsers.size());
    }

    @ApiOperation(value = "解析用户标签")
    @RequiresPermissions("wx:gzhuser:analyse")
    @GetMapping("/analyseUserTag")
    public Result analyseUserTag() {
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
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
        List<GzhUser> gzhUserList = gzhUserService.getGzhUserList(gzhAccount.getId());
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                batchAnalyseGzhUserTag(gzhUserList, gzhAccount, lock);
            }
        });
        return Result.ok().add(gzhUserList.size());
    }

    private void batchAnalyseGzhUserTag(List<GzhUser> gzhUserList, GzhAccount gzhAccount, String lock) {
        for (GzhUser gzhUser : gzhUserList) {
            analyseGzhUserTag(gzhUser, gzhAccount);
        }
        syncLock.unlock(lock);
    }

    private void analyseGzhUserTag(GzhUser user, GzhAccount gzhAccount) {
        String url = String.format(OPENID_URL, gzhAccount.getFwAppid(), gzhAccount.getFwAppsecret(), gzhAccount.getFeeType());
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
        JSONObject json = JSONUtil.parseObj(post);
        if (json.getInt("code") == 200) {
            saveFwTag(json.getJSONArray("result"), user, gzhAccount.getFwField());
        }
    }

    private void saveFwTag(JSONArray fwTagList, GzhUser user, Integer fwField) {
        List<GzhFenweiTag> gzhFenweiTagList = fwTagList.stream().map(o -> {
            JSONObject json = (JSONObject) o;
            GzhFenweiTag gzhFenweiTag = new GzhFenweiTag();
            gzhFenweiTag.setName(json.getStr("name"));
            gzhFenweiTag.setContent(json.getStr("content"));
            gzhFenweiTag.setRank(json.getStr("rank"));
            gzhFenweiTag.setScore(json.getInt("score"));
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
