package top.longmarch.wx.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.controller.WxGzhApiWraper;
import top.longmarch.wx.dao.GzhUserDao;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.entity.GzhUserHistory;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhUserHistoryService;
import top.longmarch.wx.service.IGzhUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 粉丝表 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
@Slf4j
@Service
public class GzhUserServiceImpl extends ServiceImpl<GzhUserDao, GzhUser> implements IGzhUserService {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserHistoryService gzhUserHistoryService;
    @Autowired
    private SyncLock syncLock;

    @Override
    public GzhUser getGzhUser(String openId, Long gzhId) {
        LambdaQueryWrapper<GzhUser> userWrapper = new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .eq(GzhUser::getGzhId, gzhId)
                .eq(GzhUser::getOpenId, openId);
        return this.getOne(userWrapper);
    }

    @Override
    public List<GzhUser> getGzhUserList(Long gzhId) {
        return this.list(new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .eq(GzhUser::getGzhId, gzhId));
    }

    @Override
    public void syncMoreWxGzhUser(List<Long> ids) {
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        List<GzhUser> gzhUsers = this.listByIds(ids);
        WxGzhApiWraper wxGzhApiWraper = new WxGzhApiWraper(gzhAccount);
        for (GzhUser gzhUser : gzhUsers) {
            WxMpUser wxMpUser = wxGzhApiWraper.getUserInfo(gzhUser.getOpenId());
            BeanUtils.copyProperties(wxMpUser, gzhUser);
        }
        this.updateBatchById(gzhUsers);
    }

    @Override
    public void syncBatchWxGzhUser(GzhAccount gzhAccount, String lock) {
        try {
            log.info("开始同步公众号【{}】数据", gzhAccount.getJwid());
            WxGzhApiWraper wxGzhApiWraper = new WxGzhApiWraper(gzhAccount);
            syncStart(wxGzhApiWraper, gzhAccount, null);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            syncLock.unlock(lock);
        }
    }

    private void syncStart(WxGzhApiWraper wxGzhApiWraper, GzhAccount gzhAccount, String nextOpenid) {
        WxMpUserList wxMpUserList = wxGzhApiWraper.getWxMpUserList(nextOpenid);
        log.info("粉丝总数量：count={}, openids={}", wxMpUserList.getCount(), wxMpUserList.getOpenids().size());
        if (wxMpUserList.getOpenids().size() > 0) {
            List<WxMpUser> userInfoList = wxGzhApiWraper.getUserInfoBatchList(wxMpUserList.getOpenids());
            List<GzhUser> insertGzhUserList = new ArrayList<>();
            List<GzhUser> updateGzhUserList = new ArrayList<>();
            bulidInsertOrUpdateGzhUserList(insertGzhUserList, updateGzhUserList, gzhAccount, userInfoList);
            try {
                insertOrUpdateGzhUserList(insertGzhUserList, updateGzhUserList);
            } catch (Exception e) {
                log.warn("数据异常：{}", e.getMessage());
            }
            if (StrUtil.isNotBlank(wxMpUserList.getNextOpenid())) {
                syncStart(wxGzhApiWraper, gzhAccount, wxMpUserList.getNextOpenid());
            }
        }
    }

    private void insertOrUpdateGzhUserList(List<GzhUser> insertGzhUserList, List<GzhUser> updateGzhUserList) {
        log.info("同步数量：insert={}, update={}", insertGzhUserList.size(), updateGzhUserList.size());
        if (CollectionUtil.isNotEmpty(insertGzhUserList)) {
            this.saveBatch(insertGzhUserList);
            insertGzhUserHistoryList(insertGzhUserList);
        }
        if (CollectionUtil.isNotEmpty(updateGzhUserList)) {
            this.updateBatchById(updateGzhUserList);
            insertGzhUserHistoryList(updateGzhUserList);
        }
    }

    private void insertGzhUserHistoryList(List<GzhUser> updateGzhUserList) {
        List<GzhUserHistory> insertGzhUserHistoryList = updateGzhUserList.stream().map(u -> {
            GzhUserHistory history = new GzhUserHistory();
            BeanUtils.copyProperties(u, history);
            history.setId(null);
            return history;
        }).collect(Collectors.toList());
        gzhUserHistoryService.saveBatch(insertGzhUserHistoryList);
    }

    private void bulidInsertOrUpdateGzhUserList(List<GzhUser> insertGzhUserList,
                                                List<GzhUser> updateGzhUserList,
                                                GzhAccount gzhAccount,
                                                List<WxMpUser> userInfoList) {
        for (WxMpUser wxMpUser : userInfoList) {
            GzhUser gzhUser = getGzhUser(wxMpUser.getOpenId(), gzhAccount.getId());
            if (gzhUser == null) {
                log.info("新增用户信息：openid={}, nickname={}", wxMpUser.getOpenId(), wxMpUser.getNickname());
                gzhUser = new GzhUser();
                BeanUtils.copyProperties(wxMpUser, gzhUser);
                gzhUser.setGzhId(gzhAccount.getId());
                gzhUser.setJwid(gzhAccount.getJwid());
                Integer subscribe = wxMpUser.getSubscribe()?1:0;
                gzhUser.setSubscribe(subscribe);
                insertGzhUserList.add(gzhUser);
            } else {
                if (!comparison(wxMpUser, gzhUser)) {
                    log.info("更新用户信息：openid={}, nickname={}", gzhUser.getOpenId(), gzhUser.getNickname());
                    BeanUtils.copyProperties(wxMpUser, gzhUser);
                    gzhUser.setGzhId(gzhAccount.getId());
                    gzhUser.setJwid(gzhAccount.getJwid());
                    updateGzhUserList.add(gzhUser);
                }
            }
        }
    }

    private boolean comparison(WxMpUser wxMpUser, GzhUser gzhUser) {
        String a = wxMpUser.getOpenId() + wxMpUser.getNickname() + wxMpUser.getHeadImgUrl()
                + wxMpUser.getCountry() + wxMpUser.getProvince() + wxMpUser.getCity();
        String b = gzhUser.getOpenId() + gzhUser.getNickname() + gzhUser.getHeadImgUrl()
                + gzhUser.getCountry() + gzhUser.getProvince() + gzhUser.getCity();
        return a.hashCode() == b.hashCode();
    }

}
