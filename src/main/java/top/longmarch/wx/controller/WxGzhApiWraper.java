package top.longmarch.wx.controller;

import cn.hutool.core.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
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

    public void wxTagAddBatch(Long wxTagId, List<String> openIdList) {
        List<List<String>> incise = incise(openIdList, 50);
        for (List<String> list : incise) {
            wxTagAdd(wxTagId, list);
        }
    }

    public void wxTagRemoveBatch(Long wxTagId, List<String> openIdList) {
        List<List<String>> incise = incise(openIdList, 50);
        for (List<String> list : incise) {
            wxTagRemove(wxTagId, list);
        }
    }

    public void wxTagAdd(Long wxTagId, List<String> openIdList) {
        try {
            wxMpService.getUserTagService().batchTagging(wxTagId, openIdList.toArray(new String[openIdList.size()]));
            log.info("批量打标签：wxTagId={}, openIdList={}", wxTagId, openIdList);
        } catch (WxErrorException e) {
            log.warn(e.getError().getErrorMsg());
        }
    }

    public void wxTagRemove(Long wxTagId, List<String> openIdList) {
        try {
            wxMpService.getUserTagService().batchUntagging(wxTagId, openIdList.toArray(new String[openIdList.size()]));
            log.info("批量取消标签：wxTagId={}, openIdList={}", wxTagId, openIdList);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
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
