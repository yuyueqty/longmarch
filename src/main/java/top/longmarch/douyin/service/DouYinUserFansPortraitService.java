package top.longmarch.douyin.service;

import com.douyin.open.models.FansDataFansDataInlineResponse200Data;

/**
 * https://open.douyin.com/platform/doc/OpenAPI-fans-data
 */
public interface DouYinUserFansPortraitService {

    /**
     * 获取用户粉丝画像数据
     * 接口用于查询用户的粉丝数据，如性别分布，年龄分布，地域分布等。
     *
     * @return
     */
    FansDataFansDataInlineResponse200Data fansDataGet();

}
