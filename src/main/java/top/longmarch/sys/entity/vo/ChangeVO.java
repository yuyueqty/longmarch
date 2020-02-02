package top.longmarch.sys.entity.vo;

public class ChangeVO {

    private Long id;
    private String username;
    private String password;
    private Integer status;

    public ChangeVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChangePassword{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
