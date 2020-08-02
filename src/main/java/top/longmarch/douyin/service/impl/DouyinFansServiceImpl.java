package top.longmarch.douyin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.douyin.open.models.UserFansFansInlineResponse200Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.dao.DouyinFansDao;
import top.longmarch.douyin.entity.DouyinFans;
import top.longmarch.douyin.service.DouyinFansService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DouyinFansServiceImpl extends ServiceImpl<DouyinFansDao, DouyinFans> implements DouyinFansService {

    @Override
    public void saveDouyinFans(UserFansFansInlineResponse200Data data) {
        List<DouyinFans> douyinFansList = data.getList().stream().map(userFansFansUser -> {
            DouyinFans douyinFans = new DouyinFans();
            BeanUtils.copyProperties(userFansFansUser, douyinFans);
            douyinFans.setOpenId(userFansFansUser.getOpenId());
            douyinFans.setNickname(userFansFansUser.getNickname());
            douyinFans.setUnionId(TokenUtil.unionId());
            douyinFans.setGender(userFansFansUser.getGender().getValue());
            douyinFans.setGenderStr(getGender(userFansFansUser.getGender().getValue()));
            return douyinFans;
        }).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(douyinFansList)) {
            this.saveOrUpdateBatch(douyinFansList);
        }
    }

    private String getGender(Integer value) {
        switch (value) {
            case 1:
                return "男";
            case 2:
                return "女";
            default:
                return "未知";
        }
    }

}
