package top.longmarch.wx.service.impl;

import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.dao.GzhUserDao;
import top.longmarch.wx.service.IGzhUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 粉丝表 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
@Service
public class GzhUserServiceImpl extends ServiceImpl<GzhUserDao, GzhUser> implements IGzhUserService {

}
