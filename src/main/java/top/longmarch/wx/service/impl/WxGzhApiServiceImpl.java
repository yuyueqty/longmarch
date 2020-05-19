package top.longmarch.wx.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.controller.WxGzhApiWraper;
import top.longmarch.wx.dao.GzhUserDao;
import top.longmarch.wx.entity.*;
import top.longmarch.wx.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WxGzhApiServiceImpl implements IWxGzhApiService {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhTagService gzhTagService;
    @Autowired
    private IGzhTagRuleService gzhTagRuleService;
    @Autowired
    private IGzhUserService gzhUserService;
    @Autowired
    private GzhUserDao gzhUserDao;

    @Override
    public void tagAnalysis() {
        GzhAccount gzhAccount = getGzhAccount();
        List<GzhTag> gzhTagList = getGzhTagList(gzhAccount);
        List<GzhUser> gzhUserList = getGzhUserList(gzhAccount, gzhTagList);
        Map<Long, String> userMap = buildGzhUser(gzhUserList);
        List<GzhUser> updateGzhUserList = buildUpdateGzhUserList(userMap);
        updateGzhUser(updateGzhUserList);
    }

    @Override
    public void tagRemove() {
        GzhAccount gzhAccount = getGzhAccount();
        List<GzhTag> gzhTagList = getGzhTagList(gzhAccount);
        List<GzhUser> gzhUserTagList = getGzhUserTagList(gzhAccount);
        removeGzhUserTag(gzhAccount, gzhTagList, gzhUserTagList);
    }

    private void removeGzhUserTag(GzhAccount gzhAccount, List<GzhTag> gzhTagList, List<GzhUser> gzhUserTagList) {
        WxGzhApiWraper wxGzhApiWraper = new WxGzhApiWraper(gzhAccount);
        for (GzhTag gzhTag : gzhTagList) {
            List<String> openIdList = gzhUserTagList.stream().map(GzhUser::getOpenId).collect(Collectors.toList());
            wxGzhApiWraper.wxTagRemoveBatch(gzhTag.getWxTagId(), openIdList);
        }
        removeGzhUserTag(gzhUserTagList);
    }

    private void removeGzhUserTag(List<GzhUser> gzhUserTagList) {
        gzhUserTagList.forEach(u -> {
            u.setTagIds(null);
            u.setFenWeiTags(null);
        });
        gzhUserService.updateBatchById(gzhUserTagList);
    }

    private List<GzhUser> getGzhUserTagList(GzhAccount gzhAccount) {
        return gzhUserService.list(new QueryWrapper<GzhUser>()
                .select("id, open_id, tag_ids, fen_wei_tags")
                .eq("create_by", UserUtil.getUserId())
                .eq("gzh_id", gzhAccount.getId())
                .isNotNull("fen_wei_tags"));
    }

    private void updateGzhUser(List<GzhUser> updateGzhUserList) {
        if (CollectionUtil.isNotEmpty(updateGzhUserList)) {
            gzhUserService.updateBatchById(updateGzhUserList);
        }
    }

    private List<GzhUser> buildUpdateGzhUserList(Map<Long, String> userMap) {
        List<GzhUser> updateUserList = new ArrayList<>();
        for (Map.Entry<Long, String> map : userMap.entrySet()) {
            GzhUser gzhUser = new GzhUser();
            gzhUser.setId(map.getKey());
            String[] split = map.getValue().split(",");
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
        return updateUserList;
    }

    private Map<Long, String> buildGzhUser(List<GzhUser> gzhUserList) {
        Map<Long, String> map = new HashMap<>();
        for (GzhUser gzhUser : gzhUserList) {
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
        return map;
    }

    private List<GzhUser> getGzhUserList(GzhAccount gzhAccount, List<GzhTag> gzhTagList) {
        WxGzhApiWraper wxGzhApiWraper = new WxGzhApiWraper(gzhAccount);
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
            wxGzhApiWraper.wxTagAddBatch(gzhTag.getWxTagId(), openIdList);
        }
        return result;
    }

    private List<GzhTag> getGzhTagList(GzhAccount gzhAccount) {
        return gzhTagService.list(new LambdaQueryWrapper<GzhTag>()
                .eq(GzhTag::getCreateBy, UserUtil.getUserId())
                .eq(GzhTag::getGzhId, gzhAccount.getId()));
    }

    private GzhAccount getGzhAccount() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        return gzhAccount;
    }


}
