package top.longmarch.wx.entity.export;

import lombok.Data;

import java.util.List;

@Data
public class UserExportV2 {

    private String openId;

    private String nickname;

    private String headImgUrl;

    private String sexDesc;

    private String country;

    private String province;

    private String city;

    private List<TagExportV2> tags;

}
