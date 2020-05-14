package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int page = 1, size = 100;
        int count = gzhUserService.count(new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .eq(GzhUser::getGzhId, gzhAccount.getId()));
        Map<Long, List<GzhTagRule>> ruleMap = getRule(gzhAccount.getId());
        Map<Long, String> tagMap = getGzhTag();
        if (count > size) {
            process2(page, size, gzhAccount, ruleMap, tagMap);
        } else {
            List<GzhUser> gzhUserList = gzhUserService.list(new LambdaQueryWrapper<GzhUser>()
                    .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                    .eq(GzhUser::getGzhId, gzhAccount.getId()));
            process1(gzhUserList, gzhAccount, ruleMap, tagMap);
        }
    }

    private void process1(List<GzhUser> gzhUserList, GzhAccount gzhAccount, Map<Long, List<GzhTagRule>> rules, Map<Long, String> tagMap) {
        List<GzhUser> updateGzhUserList = new ArrayList<>();
        for (GzhUser gzhUser : gzhUserList) {
            List<GzhFenweiTag> gzhFenweiTagList = gzhFenweiTagService.list(new QueryWrapper<GzhFenweiTag>()
                    .select("distinct `name`, score")
                    .eq("create_by", UserUtil.getUserId())
                    .eq("gzh_id", gzhAccount.getId())
                    .eq("open_id", gzhUser.getOpenId())
                    .groupBy("name"));
            if (gzhFenweiTagList == null || gzhFenweiTagList.size() == 0) {
                continue;
            }

            boolean flag = false;
            List<String> tagTemp = new ArrayList<>();
            for (Map.Entry<Long, List<GzhTagRule>> entry : rules.entrySet()) {
                Map<String, Integer> userMap = gzhFenweiTagList.stream().collect(Collectors.toMap(GzhFenweiTag::getName, GzhFenweiTag::getScore));

                Map<String, Integer> ruleGtMap = entry.getValue().stream().filter(k -> "gt".equals(k.getCompute())).collect(Collectors.toMap(GzhTagRule::getRid, GzhTagRule::getScore));
                boolean gt = ruleGtMap.keySet().stream().findFirst().filter(k -> ruleGtMap.get(k) > (userMap.get(k) == null ? 0 : userMap.get(k))).isPresent();
                if (!gt) {
                    tagTemp.add(tagMap.get(entry.getKey()));
                    flag = true;
                    continue;
                }

                Map<String, Integer> ruleLtMap = entry.getValue().stream().filter(k -> "lt".equals(k.getCompute())).collect(Collectors.toMap(GzhTagRule::getRid, GzhTagRule::getScore));
                boolean lt = ruleLtMap.keySet().stream().findFirst().filter(k -> ruleLtMap.get(k) > (userMap.get(k) == null ? 0 : userMap.get(k))).isPresent();
                if (lt) {
                    tagTemp.add(tagMap.get(entry.getKey()));
                    flag = true;
                    continue;
                }
            }
            if (flag && tagTemp.size() > 0) {
                gzhUser.setFenWeiTags(String.join(",", tagTemp));
                updateGzhUserList.add(gzhUser);
            }
        }
        if (updateGzhUserList.size() > 0) {
            gzhUserService.updateBatchById(updateGzhUserList);
        }
    }

    private void process2(int page, int size, GzhAccount gzhAccount, Map<Long, List<GzhTagRule>> rules, Map<Long, String> tagMap) {
        IPage<GzhUser> gzhUserPage = gzhUserService.page(new Page<>(page, size), new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .eq(GzhUser::getGzhId, gzhAccount.getId()));
        if (gzhUserPage.getRecords() != null && gzhUserPage.getRecords().size() > 0) {
            process1(gzhUserPage.getRecords(), gzhAccount, rules, tagMap);
            process2((page+1), size, gzhAccount, rules, tagMap);
        }
    }

    private Map<Long, String> getGzhTag() {
        return gzhTagService.list().stream().collect(Collectors.toMap(GzhTag::getId, GzhTag::getName));
    }

    private Map<Long, List<GzhTagRule>> getRule(Long gzhId) {
        List<GzhTagRule> gzhTagRuleList = gzhTagRuleService.list(new LambdaQueryWrapper<GzhTagRule>()
                .eq(GzhTagRule::getCreateBy, UserUtil.getUserId())
                .eq(GzhTagRule::getGzhId, gzhId));
        return gzhTagRuleList.stream().collect(Collectors.groupingBy(GzhTagRule::getTagId, Collectors.toList()));
    }

}
