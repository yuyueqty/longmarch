package top.longmarch.wx.entity;

import java.util.List;

public class FwTagDTO {

    private String msg;
    private Integer code;
    private List<FwTag> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<FwTag> getResult() {
        return result;
    }

    public void setResult(List<FwTag> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FwTagDTO{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", result=" + result +
                '}';
    }
}
