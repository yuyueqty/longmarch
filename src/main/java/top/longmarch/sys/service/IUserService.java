package top.longmarch.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.longmarch.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public interface IUserService extends IService<User> {

    IPage<User> search(Map<String, Object> params);

    void saveUser(User user);

    void updateUser(User user);

    void removeUser(List<Long> asList);
}
