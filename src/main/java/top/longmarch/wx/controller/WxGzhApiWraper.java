package top.longmarch.wx.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import top.longmarch.wx.entity.GzhAccount;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WxGzhApiWraper {

    private WxMpService wxMpService;

    public WxGzhApiWraper(GzhAccount gzhAccount) {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(gzhAccount.getWeixinAppid());
        config.setSecret(gzhAccount.getWeixinAppsecret());

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        this.wxMpService = wxMpService;
    }

    public void tagDeleteBatch(List<Long> tagIds) {
        for (Long tagId : tagIds) {
            tagDelete(tagId);
        }
    }

    public void tagDelete(Long tagId) {
        try {
            wxMpService.getUserTagService().tagDelete(tagId);
            log.info("删除标签：tagId={}", tagId);
        } catch (WxErrorException e) {
            log.warn(e.getError().getErrorMsg());
        }
    }

    public WxUserTag tagCreate(String tagName) {
        WxUserTag wxUserTag = null;
        try {
            wxUserTag = wxMpService.getUserTagService().tagCreate(tagName);
            log.info("创建标签：tagName={}", tagName);
        } catch (WxErrorException e) {
            log.warn(e.getError().getErrorMsg());
        }
        return wxUserTag;
    }

    public void tagUpdate(Long tagId, String tagName) {
        try {
            wxMpService.getUserTagService().tagUpdate(tagId, tagName);
            log.info("修改标签：tagId={}, tagName={}", tagId, tagName);
        } catch (WxErrorException e) {
            log.warn(e.getError().getErrorMsg());
        }
    }

    public void wxUserTagAddBatch(Long wxTagId, List<String> openIdList) {
        List<List<String>> incise = incise(openIdList, 50);
        for (List<String> list : incise) {
            wxUserTagAdd(wxTagId, list);
        }
    }

    public void wxUserTagRemoveBatch(Long wxTagId, List<String> openIdList) {
        List<List<String>> incise = incise(openIdList, 50);
        for (List<String> list : incise) {
            wxUserTagRemove(wxTagId, list);
        }
    }

    public void wxUserTagAdd(Long wxTagId, List<String> openIdList) {
        try {
            wxMpService.getUserTagService().batchTagging(wxTagId, openIdList.toArray(new String[openIdList.size()]));
            log.info("批量打标签：wxTagId={}, openIdList={}", wxTagId, openIdList);
        } catch (WxErrorException e) {
            log.warn(e.getError().getErrorMsg());
        }
    }

    public void wxUserTagRemove(Long wxTagId, List<String> openIdList) {
        try {
            wxMpService.getUserTagService().batchUntagging(wxTagId, openIdList.toArray(new String[openIdList.size()]));
            log.info("批量取消标签：wxTagId={}, openIdList={}", wxTagId, openIdList);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
    }

    public WxMpUserList getWxMpUserList(String nextOpenid) {
        WxMpUserList wxMpUserList = null;
        try {
            wxMpUserList = wxMpService.getUserService().userList(nextOpenid);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return wxMpUserList;
    }

    public List<WxMpUser> getUserInfoBatchList(List<String> openidList) {
        List<WxMpUser> userInfoList = new ArrayList<>();
        List<List<String>> incise = incise(openidList, 100);
        for (List<String> list : incise) {
            List<WxMpUser> wxMpUserList = getUserInfoList(list);
            if (CollectionUtil.isNotEmpty(wxMpUserList)) {
                userInfoList.addAll(wxMpUserList);
            }
        }
        return userInfoList;
    }

    public List<WxMpUser> getUserInfoList(List<String> openidList) {
        List<WxMpUser> userInfoList = null;
        try {
            userInfoList = wxMpService.getUserService().userInfoList(openidList);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return userInfoList;
    }

    public WxMpUser getUserInfo(String openid) {
        WxMpUser wxMpUser = null;
        try {
            wxMpUser = wxMpService.getUserService().userInfo(openid);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return wxMpUser;
    }

    private List<List<String>> incise(List<String> openIdList, Integer pageSize) {
        List<List<String>> list = new ArrayList<>();
        if (openIdList.size() > pageSize) {
            int pages = PageUtil.totalPage(openIdList.size(), pageSize);
            for (int i = 0; i < pages; i++) {
                List<String> openIdSubList;
                if (i == (pages - 1)) {
                    openIdSubList = openIdList.subList(i * pageSize + 1, (openIdList.size() - 1));
                } else {
                    openIdSubList = openIdList.subList(i * pageSize + 1, i * i * pageSize + pageSize);
                }
                list.add(openIdSubList);
            }
        } else {
            list.add(openIdList);
        }
        return list;
    }

}
