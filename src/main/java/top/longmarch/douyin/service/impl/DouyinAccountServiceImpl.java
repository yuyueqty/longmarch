package top.longmarch.douyin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.douyin.dao.DouyinAccountDao;
import top.longmarch.douyin.entity.DouyinAccount;
import top.longmarch.douyin.service.DouYinUserReportDataService;
import top.longmarch.douyin.service.DouYinUserService;
import top.longmarch.douyin.service.DouyinAccountService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DouyinAccountServiceImpl extends ServiceImpl<DouyinAccountDao, DouyinAccount> implements DouyinAccountService {

    @Autowired
    private DouYinUserService douYinUserService;
    @Autowired
    private DouYinUserReportDataService douYinUserReportDataService;

    @Override
    public void saveOrUpdateDouyinAccount(String token) {
//        try {
//            Integer dateType = 7;
//            OauthUserinfoResponse oauthUserinfo = douYinUserService.getOauthUserinfo();
//            OauthUserinfoResponseData oauthUserinfoResponseData = oauthUserinfo.getData();
//            DouyinAccount tiaoYueUser = new DouyinAccount();
//            BeanUtils.copyProperties(oauthUserinfoResponseData, tiaoYueUser);
//
//            DataExternalUserFansResponseData fansResponse = douYinUserReportDataService.dataExternalUserFansGet(dateType).getData();
//            List<ExternalUserFans> resultList = fansResponse.getResultList();
//            if (CollectionUtil.isNotEmpty(resultList)) {
//                ExternalUserFans externalUserFans = resultList.get(resultList.size() - 1);
//                tiaoYueUser.setFansNum(externalUserFans.getTotalFans());
//            }
//            DataExternalUserCommentResponseData commentResponse = douYinUserReportDataService.dataExternalUserCommentGet(dateType).getData();
//            List<ExternalUserComment> resultList1 = commentResponse.getResultList();
//            if (CollectionUtil.isNotEmpty(resultList1)) {
//                ExternalUserComment externalUserComment = resultList1.get(resultList1.size() - 1);
//                tiaoYueUser.setCommentNum(externalUserComment.getNewComment());
//            }
//            DataExternalUserLikeResponseData likeResponse = douYinUserReportDataService.dataExternalUserLikeGet(dateType).getData();
//            List<ExternalUserLike> resultList2 = likeResponse.getResultList();
//            if (CollectionUtil.isNotEmpty(resultList2)) {
//                ExternalUserLike externalUserLike = resultList2.get(resultList2.size() - 1);
//                tiaoYueUser.setLikeNum(externalUserLike.getNewLike());
//            }
//            DataExternalUserShareResponseData shareResponse = douYinUserReportDataService.dataExternalUserShareGet(dateType).getData();
//            List<ExternalUserShare> resultList3 = shareResponse.getResultList();
//            if (CollectionUtil.isNotEmpty(resultList3)) {
//                ExternalUserShare externalUserShare = resultList3.get(resultList3.size() - 1);
//                tiaoYueUser.setShareNum(externalUserShare.getNewShare());
//            }
//            DataExternalUserProfileResponseData profileResponse = douYinUserReportDataService.dataExternalUserProfileGet(dateType).getData();
//            List<ExternalUserProfile> resultList4 = profileResponse.getResultList();
//            if (CollectionUtil.isNotEmpty(resultList4)) {
//                ExternalUserProfile externalUserProfile = resultList4.get(resultList4.size() - 1);
//                tiaoYueUser.setProfileNum(externalUserProfile.getProfileUv());
//            }
//            this.saveOrUpdate(tiaoYueUser);
//        } catch (ApiException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void setDefault(String openId) {
        LambdaQueryWrapper<DouyinAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DouyinAccount::getOpenId, openId).eq(DouyinAccount::getCreateBy, UserUtil.getUserId());
        DouyinAccount one = this.getOne(wrapper);
        if (one != null) {
            List<DouyinAccount> list = this.list(new LambdaQueryWrapper<DouyinAccount>().eq(DouyinAccount::getCreateBy, UserUtil.getUserId()));
            List<DouyinAccount> collect = list.stream().map(douyinAccount -> {
                DouyinAccount douyinAccountNew = new DouyinAccount();
                douyinAccountNew.setOpenId(douyinAccount.getOpenId());
                if (douyinAccount.getOpenId().equals(openId)) {
                    douyinAccountNew.setDefaultAccount(1);
                } else {
                    douyinAccountNew.setDefaultAccount(0);
                }
                return douyinAccountNew;
            }).collect(Collectors.toList());
            this.updateBatchById(collect);
            TokenUtil.set(one);
        }
    }

}
