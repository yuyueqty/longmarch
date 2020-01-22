package top.longmarch.core.shiro.model;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class RegisterUser implements Serializable {

    private static final String PHONE = "(?:0|86|\\+86)?1[3456789]\\d{9}";

    private String username;
    private String password;
    @Pattern(regexp = PHONE, message = "手机号不正确")
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "username='" + username + '\'' +
                ", password=***'" +
                ", phone='" + phone + '\'' +
                '}';
    }

}
