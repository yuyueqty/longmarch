package top.longmarch.sys.entity.vo;

public class ChangeUserPasswordDTO {

    private String username;
    private String password;

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

    @Override
    public String toString() {
        return "ChangeUserPasswordVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
