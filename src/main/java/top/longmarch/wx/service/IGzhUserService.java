package top.longmarch.wx.service;

import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 粉丝表 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
public interface IGzhUserService extends IService<GzhUser> {

    GzhUser getGzhUser(String openId, Long gzhId);

    List<GzhUser> getGzhUserList(Long gzhId);

    void syncBatchWxGzhUser(GzhAccount gzhAccount, String lock);

    void syncMoreWxGzhUser(List<Long> ids);

}
