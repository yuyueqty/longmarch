package top.longmarch.sys.entity.dto;

import lombok.Data;
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
        user.setPassword(password);
        return user;
    }

}
