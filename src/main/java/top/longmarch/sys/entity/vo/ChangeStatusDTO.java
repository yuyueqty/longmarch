package top.longmarch.sys.entity.vo;

public class ChangeStatusDTO {

    private Long id;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChangeStatusVO{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
