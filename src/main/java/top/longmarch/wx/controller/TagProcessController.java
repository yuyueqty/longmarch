package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.*;
import top.longmarch.wx.service.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 公众号粉丝分维解析标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
 */
@Api(value = "解析新标签", tags = "解析新标签")
@RestController
@RequestMapping("/wx/gzh-user")
public class TagProcessController {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhFenweiTagService gzhFenweiTagService;
    @Autowired
    private IGzhTagService gzhTagService;
    @Autowired
    private IGzhTagRuleService gzhTagRuleService;
    @Autowired
    private IGzhUserService gzhUserService;

    @GetMapping("/process")
    public Result process() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                process0(gzhAccount);
            }
        });
        return Result.ok();
    }

    private void process0(GzhAccount gzhAccount) {
        IPage<GzhFenweiTag> page = new Page<>(1, 100);
        LambdaQueryWrapper<GzhFenweiTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GzhFenweiTag::getCreateBy, UserUtil.getUserId())
                .eq(GzhFenweiTag::getGzhId, gzhAccount.getId())
                .eq(GzhFenweiTag::getFieldId, gzhAccount.getFwField());
        IPage<GzhFenweiTag> gzhFenweiTagPage = gzhFenweiTagService.page(page, wrapper);
        Map<Long, List<GzhTagRule>> ruleMap = getRule(gzhAccount.getId());
        Map<Long, String> tagMap = getGzhTag();
        if (gzhFenweiTagPage.getTotal() > 50) {
            for (long p = 2; p < page.getPages(); p++) {
                process2(p, gzhAccount, page.getRecords(), ruleMap, tagMap);
            }
        } else {
            process1(gzhFenweiTagPage.getRecords(), ruleMap, tagMap);
        }
    }

    private void process1(List<GzhFenweiTag> records, Map<Long, List<GzhTagRule>> rules, Map<Long, String> tagMap) {
        Map<String, List<GzhFenweiTag>> collect = records.stream().collect(Collectors.groupingBy(GzhFenweiTag::getOpenId, Collectors.toList()));
        for (Map.Entry<String, List<GzhFenweiTag>> entry : collect.entrySet()) {
            GzhUser gzhUser = getTag(entry.getValue(), rules, tagMap);
            if (gzhUser != null) {
                gzhUserService.update(new UpdateWrapper<GzhUser>().lambda().set(GzhUser::getFenWeiTags, gzhUser.getFenWeiTags()).eq(GzhUser::getOpenId, gzhUser.getOpenId()));
            }
        }
    }

    private void process2(long p, GzhAccount gzhAccount, List<GzhFenweiTag> records, Map<Long, List<GzhTagRule>> rules, Map<Long, String> tagMap) {
        IPage<GzhFenweiTag> page = new Page<>(p, 100);
        LambdaQueryWrapper<GzhFenweiTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GzhFenweiTag::getCreateBy, UserUtil.getUserId())
                .eq(GzhFenweiTag::getGzhId, gzhAccount.getId())
                .eq(GzhFenweiTag::getFieldId, gzhAccount.getFwField());
        IPage<GzhFenweiTag> gzhFenweiTagPage = gzhFenweiTagService.page(page, wrapper);
        process1(records, rules, tagMap);
        if (gzhFenweiTagPage.getRecords().size() > 0) {
            process2((p + 1), gzhAccount, gzhFenweiTagPage.getRecords(), rules, tagMap);
        }
    }

    private GzhUser getTag(List<GzhFenweiTag> tags, Map<Long, List<GzhTagRule>> rules, Map<Long, String> tagMap) {
        List<String> tagNewList = new ArrayList<>();
        String openId = null;
        for (Map.Entry<Long, List<GzhTagRule>> entry : rules.entrySet()) {
            List<GzhTagRule> list = entry.getValue();
            Map<String, Integer> ruleMap = list.stream().collect(Collectors.toMap(GzhTagRule::getRid, GzhTagRule::getScore));
            Map<String, Integer> userMap = tags.stream().collect(Collectors.toMap(GzhFenweiTag::getName, GzhFenweiTag::getScore));
            boolean present = ruleMap.keySet().stream().findFirst().filter(k -> ruleMap.get(k) > (userMap.get(k) == null ? 0 : userMap.get(k))).isPresent();
            if (!present) {
                openId = tags.get(0).getOpenId();
                tagNewList.add(tagMap.get(entry.getKey()));
            }
        }

        GzhUser gzhUser = null;
        if (tagNewList.size() > 0 && StrUtil.isNotBlank(openId)) {
            gzhUser = new GzhUser();
            gzhUser.setOpenId(openId);
            gzhUser.setFenWeiTags(String.join(",", tagNewList));
        }
        return gzhUser;
    }

    private Map<Long, String> getGzhTag() {
        return gzhTagService.list().stream().collect(Collectors.toMap(GzhTag::getId, GzhTag::getName));
    }

    private Map<Long, List<GzhTagRule>> getRule(Long gzhId) {
        List<GzhTagRule> gzhTagRuleList = gzhTagRuleService.list(new LambdaQueryWrapper<GzhTagRule>().eq(GzhTagRule::getCreateBy, UserUtil.getUserId()).eq(GzhTagRule::getGzhId, gzhId));
        return gzhTagRuleList.stream().collect(Collectors.groupingBy(GzhTagRule::getTagId, Collectors.toList()));
    }

}
