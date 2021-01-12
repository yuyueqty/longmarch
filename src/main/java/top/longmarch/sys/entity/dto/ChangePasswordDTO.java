package top.longmarch.sys.entity.dto;

import lombok.Data;
import top.longmarch.core.utils.PasswordUtil;
import top.longmarch.sys.entity.User;

@Data
public class ChangePasswordDTO {

    private Long id;
    private String username;
    private String password;

    public User convertUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(PasswordUtil.password(password));
        return user;
    }

}
