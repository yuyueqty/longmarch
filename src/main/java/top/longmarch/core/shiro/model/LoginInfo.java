package top.longmarch.core.shiro.model;

import lombok.Data;

@Data
public class LoginInfo {

    private String username;
    private String password;
    private Boolean rememberMe;

}
