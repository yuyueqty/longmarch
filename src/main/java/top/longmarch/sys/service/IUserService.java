package top.longmarch.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.longmarch.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.sys.entity.dto.ChangeStatusDTO;
import top.longmarch.sys.entity.dto.ChangePasswordDTO;
import top.longmarch.sys.entity.dto.CreateUpdateUserDTO;

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

    void updateUserLoginInfo(Long userId);

    void changePassword(ChangePasswordDTO changePasswordDTO);

    void changeStatus(ChangeStatusDTO changeStatusDTO);

    void createUpdateUser(CreateUpdateUserDTO createUpdateUserDTO);
}
