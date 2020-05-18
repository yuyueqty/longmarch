package top.longmarch.wx.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.dao.GzhUserDao;
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
@Slf4j
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
    @Autowired
    private GzhUserDao gzhUserDao;

    @GetMapping("/process")
    public Result process() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        List<GzhTag> gzhTagList = gzhTagService.list(new LambdaQueryWrapper<GzhTag>()
                .eq(GzhTag::getCreateBy, UserUtil.getUserId())
                .eq(GzhTag::getGzhId, gzhAccount.getId()));

        List<GzhUser> result = new ArrayList<>();
        for (GzhTag gzhTag : gzhTagList) {
            int count = gzhTagRuleService.count(new LambdaQueryWrapper<GzhTagRule>().eq(GzhTagRule::getTagId, gzhTag.getId()));
            WxParams wxParams = new WxParams();
            wxParams.setUserId(UserUtil.getUserId());
            wxParams.setGzhId(gzhAccount.getId());
            wxParams.setTagId(gzhTag.getId());
            wxParams.setSize(count);
            List<GzhUser> userNewTags = gzhUserDao.getUserNewTags(wxParams);
            result.addAll(userNewTags);
            List<String> openIdList = userNewTags.stream().map(GzhUser::getOpenId).collect(Collectors.toList());
            updateRemoteWxTag(gzhTag.getWxTagId(), openIdList, getWxMpService(gzhAccount));
        }

        Map<Long, String> map = new HashMap<>();
        for (GzhUser gzhUser : result) {
            Long key = gzhUser.getId();
            String value_new = gzhUser.getTagIds() + ":" + gzhUser.getFenWeiTags();
            String value_old = map.get(key);
            if (StrUtil.isNotBlank(value_old)) {
                String[] split1 = value_old.split(",");
                String[] split2 = split1[split1.length - 1].split(":");
                String tagIds = split2[0];
                String fenWeiTags = split2[1];
                gzhUser.setTagIds(tagIds + "," + gzhUser.getTagIds());
                gzhUser.setFenWeiTags(fenWeiTags + "," + gzhUser.getFenWeiTags());
                map.put(key, value_old + "," + value_new);
            } else {
                map.put(key, gzhUser.getTagIds() + ":" + gzhUser.getFenWeiTags());
            }
        }

        List<GzhUser> updateUserList = new ArrayList<>();
        for (Map.Entry<Long, String> userMap : map.entrySet()) {
            GzhUser gzhUser = new GzhUser();
            gzhUser.setId(userMap.getKey());
            String[] split = userMap.getValue().split(",");
            List<String> tagIdList = new ArrayList<>();
            List<String> fenWeiTagList = new ArrayList<>();
            for (String s : split) {
                String[] split1 = s.split(":");
                tagIdList.add(split1[0]);
                fenWeiTagList.add(split1[1]);
            }
            gzhUser.setTagIds(String.join(",", tagIdList));
            gzhUser.setFenWeiTags(String.join(",", fenWeiTagList));
            updateUserList.add(gzhUser);
        }
        if (CollectionUtil.isNotEmpty(updateUserList)) {
            gzhUserService.updateBatchById(updateUserList);
        }
        return Result.ok().add(updateUserList);
    }

    @GetMapping("/process_bak")
    public Result process_bak() {
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

    @GetMapping("/wxuserTagRemove")
    public Result wxuserTagRemove() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                remove0(gzhAccount);
            }
        });
        return Result.ok();
    }

    private void remove0(GzhAccount gzhAccount) {
        QueryWrapper<GzhUser> wrapper = new QueryWrapper<>();
        wrapper.select("id, open_id, tag_ids, fen_wei_tags")
                .eq("create_by", UserUtil.getUserId())
                .eq("gzh_id", gzhAccount.getId())
                .isNotNull("fen_wei_tags");
        List<GzhUser> gzhUserList = gzhUserService.list(wrapper);

        List<GzhTag> gzhTagList = gzhTagService.list(new LambdaQueryWrapper<GzhTag>()
                .eq(GzhTag::getGzhId, gzhAccount.getId()));

        WxMpService wxMpService = getWxMpService(gzhAccount);
        for (GzhTag gzhTag : gzhTagList) {
            List<String> openids = gzhUserList.stream().filter(u -> u.getTagIds()
                    .contains(gzhTag.getWxTagId() + "")).map(u -> u.getOpenId()).collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(openids)) {
                // 每次传入的openid列表个数不能超过50个
                if (openids.size() > 50) {
                    int pages = PageUtil.totalPage(openids.size(), 50);
                    for (int i = 0; i < pages; i++) {
                        List<String> openIdSubList;
                        if (i == (pages - 1)) {
                            openIdSubList = openids.subList(i * 50 + 1, (openids.size() - 1));
                        } else {
                            openIdSubList = openids.subList(i * 50 + 1, i * i * 50 + 50);
                        }
                        removeRemoteWxTag(gzhTag.getWxTagId(), openIdSubList, wxMpService);
                    }
                } else {
                    removeRemoteWxTag(gzhTag.getWxTagId(), openids, wxMpService);
                }
            }
        }

        for (GzhUser gzhUser : gzhUserList) {
            gzhUser.setFenWeiTags(null);
            gzhUser.setTagIds(null);
        }
        gzhUserService.updateBatchById(gzhUserList);
    }

    private void removeRemoteWxTag(Long wxTagId, List<String> openids, WxMpService wxMpService) {
        try {
            wxMpService.getUserTagService().batchUntagging(wxTagId, openids.toArray(new String[openids.size()]));
            log.info("批量取消标签：tagId={}, openids={}", wxTagId, openids);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
    }

    private void process0(GzhAccount gzhAccount) {
        int page = 1, size = 100;
        List<GzhUser> gzhUserList = getGzhUserList(gzhAccount);
        WxMpService wxMpService = getWxMpService(gzhAccount);
        List<GzhTag> gzhTagList = gzhTagService.list(new LambdaQueryWrapper<GzhTag>().eq(GzhTag::getGzhId, gzhAccount.getId()));
        Map<Long, List<GzhTagRule>> ruleMap = getRule(gzhAccount.getId());
        Map<Long, String> tagMap = getGzhTag(gzhTagList);
        Map<Long, String> wxTagMap = getWxGzhTag(gzhTagList);
        log.info("批量解析营销标签：user_count={}, tag_count={}", gzhUserList.size(), gzhTagList.size());
        if (gzhUserList.size() > size) {
            process2(page, size, gzhAccount, ruleMap, tagMap, wxTagMap, wxMpService);
        } else {
            process1(gzhUserList, gzhAccount, ruleMap, tagMap, wxTagMap, wxMpService);
        }
    }

    private void process1(List<GzhUser> gzhUserList,
                          GzhAccount gzhAccount,
                          Map<Long, List<GzhTagRule>> rules,
                          Map<Long, String> tagMap,
                          Map<Long, String> wxTagMap,
                          WxMpService wxMpService) {
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
            // 分维标签名和评分
            Map<String, Integer> userMap = gzhFenweiTagList.stream().collect(Collectors.toMap(GzhFenweiTag::getName, GzhFenweiTag::getScore));

            boolean flag = true;
            List<String> tagNames = new ArrayList<>();
            List<String> tagIds = new ArrayList<>();
            for (Map.Entry<Long, List<GzhTagRule>> entry : rules.entrySet()) {
                // 营销标签名称
                String tagName = tagMap.get(entry.getKey());
                Long tagId = null;
                for (Map.Entry<Long, String> entry1 : wxTagMap.entrySet()) {
                    if (entry1.getValue().equals(tagName)) {
                        tagId = entry1.getKey();
                        break;
                    }
                }

                // 评分大于阈值
                Map<String, Integer> ruleGtMap = entry.getValue().stream().filter(k -> "gt".equals(k.getCompute())).collect(Collectors.toMap(GzhTagRule::getRid, GzhTagRule::getScore));
                for (Map.Entry<String, Integer> entry2 : ruleGtMap.entrySet()) {
                    Integer userScore = userMap.get(entry2.getKey()) == null ? 0 : userMap.get(entry2.getKey());
                    Integer ruleScore = entry2.getValue();
                    log.info("匹配评分大于阈值参数：tag_id={}, tag_name={}, user_name={}, userScore={}, ruleScore={}, compute=gt",
                            tagId, tagName, gzhUser.getNickname(), userScore, ruleScore);
                    if (ruleScore > userScore) {
                        flag = false;
                        break;
                    }
                }

                // 评分小于阈值
                if (flag) {
                    Map<String, Integer> ruleLtMap = entry.getValue().stream().filter(k -> "lt".equals(k.getCompute())).collect(Collectors.toMap(GzhTagRule::getRid, GzhTagRule::getScore));
                    for (Map.Entry<String, Integer> entry3 : ruleLtMap.entrySet()) {
                        Integer userScore = userMap.get(entry3.getKey()) == null ? 0 : userMap.get(entry3.getKey());
                        Integer ruleScore = entry3.getValue();
                        log.info("匹配评分小于阈值参数：tag_id={}, tag_name={}, user_name={}, userScore={}, ruleScore={}, compute=lt",
                                tagId, tagName, gzhUser.getNickname(), userScore, ruleScore);
                        if (userScore > ruleScore) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    tagNames.add(tagName);
                    tagIds.add(tagId + "");
                    log.info("匹配成功参数：user_name={}, tag_id={}, tag_name={}, flag={}", gzhUser.getNickname(), tagId, tagName, flag);
                }
            }
            if (flag && tagNames.size() > 0) {
                gzhUser.setTagIds(String.join(",", tagIds));
                gzhUser.setFenWeiTags(String.join(",", tagNames));
                updateGzhUserList.add(gzhUser);
            }
        }
        if (updateGzhUserList.size() > 0) {
            log.info("批量更新营销标签：count={}", updateGzhUserList.size());
            updateRemoteWxTag(updateGzhUserList, wxTagMap, wxMpService);
            gzhUserService.updateBatchById(updateGzhUserList);
        }
    }

    private void updateRemoteWxTag(List<GzhUser> updateGzhUserList, Map<Long, String> wxTagMap, WxMpService wxMpService) {
        for (Map.Entry<Long, String> entry : wxTagMap.entrySet()) {
            List<String> openIdList = updateGzhUserList.stream().filter(u -> Arrays.asList(u.getFenWeiTags().split(","))
                    .contains(entry.getValue())).map(u -> u.getOpenId()).collect(Collectors.toList());
            // 标签功能目前支持公众号为用户打上最多20个标签。
            List<String> updateOpenIdList = openIdList.stream().filter(openId -> {
                try {
                    return wxMpService.getUserTagService().userTagList(openId).size() < 20;
                } catch (WxErrorException e) {
                    log.warn(openId, e.getError().getErrorMsg());
                    return false;
                }
            }).collect(Collectors.toList());

            // 40032  每次传入的openid列表个数不能超过50个
            if (CollectionUtil.isNotEmpty(updateOpenIdList)) {

                if (updateOpenIdList.size() > 50) {
                    int pages = PageUtil.totalPage(updateOpenIdList.size(), 50);
                    for (int i = 0; i < pages; i++) {
                        List<String> openIdSubList;
                        if (i == (pages - 1)) {
                            openIdSubList = updateOpenIdList.subList(i * 50 + 1, (updateOpenIdList.size() - 1));
                        } else {
                            openIdSubList = updateOpenIdList.subList(i * 50 + 1, i * i * 50 + 50);
                        }
                        updateRemoteWxTag(entry.getKey(), openIdSubList, wxMpService);
                    }
                } else {
                    updateRemoteWxTag(entry.getKey(), updateOpenIdList, wxMpService);
                }
            }
        }
    }

    private void updateRemoteWxTag(Long tagId, List<String> openids, WxMpService wxMpService) {
        try {
            wxMpService.getUserTagService().batchTagging(tagId, openids.toArray(new String[openids.size()]));
            log.info("批量打标签：tagId={}, openids={}", tagId, openids);
        } catch (WxErrorException e) {
            log.warn(e.getError().getErrorMsg());
        }
    }


    private void process2(int page, int size,
                          GzhAccount gzhAccount,
                          Map<Long, List<GzhTagRule>> rules,
                          Map<Long, String> tagMap,
                          Map<Long, String> wxTagMap,
                          WxMpService wxMpService) {
        IPage<GzhUser> gzhUserPage = getGzhUserList(gzhAccount, page, size);
        if (gzhUserPage.getRecords() != null && gzhUserPage.getRecords().size() > 0) {
            process1(gzhUserPage.getRecords(), gzhAccount, rules, tagMap, wxTagMap, wxMpService);
            process2((page + 1), size, gzhAccount, rules, tagMap, wxTagMap, wxMpService);
        }
    }

    private Map<Long, String> getGzhTag(List<GzhTag> gzhTagList) {
        return gzhTagList.stream().collect(Collectors.toMap(GzhTag::getId, GzhTag::getName));
    }

    private Map<Long, String> getWxGzhTag(List<GzhTag> gzhTagList) {
        return gzhTagList.stream().collect(Collectors.toMap(GzhTag::getWxTagId, GzhTag::getName));
    }

    private Map<Long, List<GzhTagRule>> getRule(Long gzhId) {
        List<GzhTagRule> gzhTagRuleList = gzhTagRuleService.list(new LambdaQueryWrapper<GzhTagRule>()
                .eq(GzhTagRule::getCreateBy, UserUtil.getUserId())
                .eq(GzhTagRule::getGzhId, gzhId));
        return gzhTagRuleList.stream().collect(Collectors.groupingBy(GzhTagRule::getTagId, Collectors.toList()));
    }

    private List<GzhUser> getGzhUserList(GzhAccount gzhAccount) {
        return gzhUserService.list(new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .eq(GzhUser::getGzhId, gzhAccount.getId()));
    }

    private Page<GzhUser> getGzhUserList(GzhAccount gzhAccount, int page, int size) {
        return gzhUserService.page(new Page<>(page, size), new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .eq(GzhUser::getGzhId, gzhAccount.getId()));
    }

    private WxMpService getWxMpService(GzhAccount gzhAccount) {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(gzhAccount.getWeixinAppid());
        config.setSecret(gzhAccount.getWeixinAppsecret());

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

}
