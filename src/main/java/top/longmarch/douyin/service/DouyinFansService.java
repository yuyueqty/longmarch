package top.longmarch.douyin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.douyin.open.models.UserFansFansInlineResponse200Data;
import top.longmarch.douyin.entity.DouyinFans;

public interface DouyinFansService extends IService<DouyinFans> {

    void saveDouyinFans(UserFansFansInlineResponse200Data douyinFans);

}
