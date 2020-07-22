package top.longmarch.douyin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.douyin.open.ApiException;
import com.douyin.open.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.dao.DouyinAccountDao;
import top.longmarch.douyin.entity.DouyinAccount;
import top.longmarch.douyin.service.DouYinUserReportDataService;
import top.longmarch.douyin.service.DouYinUserService;
import top.longmarch.douyin.service.DouyinAccountService;

import java.util.List;

@Slf4j
@Service
public class DouyinAccountServiceImpl extends ServiceImpl<DouyinAccountDao, DouyinAccount> implements DouyinAccountService {

    @Autowired
    private DouYinUserService douYinUserService;
    @Autowired
    private DouYinUserReportDataService douYinUserReportDataService;

    @Override
    public void saveOrUpdateDouyinAccount(String token) {
        try {
            Integer dateType = 7;
            OauthUserinfoResponse oauthUserinfo = douYinUserService.getOauthUserinfo(token);
            OauthUserinfoResponseData oauthUserinfoResponseData = oauthUserinfo.getData();
            DouyinAccount tiaoYueUser = new DouyinAccount();
            BeanUtils.copyProperties(oauthUserinfoResponseData, tiaoYueUser);

            DataExternalUserFansResponseData fansResponse = douYinUserReportDataService.dataExternalUserFansGet(token, dateType).getData();
            List<ExternalUserFans> resultList = fansResponse.getResultList();
            if (CollectionUtil.isNotEmpty(resultList)) {
                ExternalUserFans externalUserFans = resultList.get(resultList.size() - 1);
                tiaoYueUser.setFansNum(externalUserFans.getTotalFans());
            }
            DataExternalUserCommentResponseData commentResponse = douYinUserReportDataService.dataExternalUserCommentGet(token, dateType).getData();
            List<ExternalUserComment> resultList1 = commentResponse.getResultList();
            if (CollectionUtil.isNotEmpty(resultList1)) {
                ExternalUserComment externalUserComment = resultList1.get(resultList1.size() - 1);
                tiaoYueUser.setCommentNum(externalUserComment.getNewComment());
            }
            DataExternalUserLikeResponseData likeResponse = douYinUserReportDataService.dataExternalUserLikeGet(token, dateType).getData();
            List<ExternalUserLike> resultList2 = likeResponse.getResultList();
            if (CollectionUtil.isNotEmpty(resultList2)) {
                ExternalUserLike externalUserLike = resultList2.get(resultList2.size() - 1);
                tiaoYueUser.setLikeNum(externalUserLike.getNewLike());
            }
            DataExternalUserShareResponseData shareResponse = douYinUserReportDataService.dataExternalUserShareGet(token, dateType).getData();
            List<ExternalUserShare> resultList3 = shareResponse.getResultList();
            if (CollectionUtil.isNotEmpty(resultList3)) {
                ExternalUserShare externalUserShare = resultList3.get(resultList3.size() - 1);
                tiaoYueUser.setShareNum(externalUserShare.getNewShare());
            }
            DataExternalUserProfileResponseData profileResponse = douYinUserReportDataService.dataExternalUserProfileGet(token, dateType).getData();
            List<ExternalUserProfile> resultList4 = profileResponse.getResultList();
            if (CollectionUtil.isNotEmpty(resultList4)) {
                ExternalUserProfile externalUserProfile = resultList4.get(resultList4.size() - 1);
                tiaoYueUser.setProfileNum(externalUserProfile.getProfileUv());
            }
            this.saveOrUpdate(tiaoYueUser);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
