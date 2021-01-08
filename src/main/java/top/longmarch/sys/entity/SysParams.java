package top.longmarch.sys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysParams implements Serializable {

    private String title;
    private String headImgUrl;
    private String defaultNickname;

}
