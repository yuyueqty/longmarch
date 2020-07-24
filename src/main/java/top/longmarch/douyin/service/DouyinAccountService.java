package top.longmarch.douyin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.douyin.entity.DouyinAccount;

public interface DouyinAccountService extends IService<DouyinAccount> {

    void saveOrUpdateDouyinAccount(String token);

    void setDefault(String openId);
}
