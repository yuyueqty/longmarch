package top.longmarch.wx.entity.export;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

@Data
@ExcelTarget("userExport")
public class UserExport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "OpenID", orderNum = "1")
    private String openId;

    @Excel(name = "昵称", orderNum = "2")
    private String nickname;

    @Excel(name = "头像", orderNum = "3")
    private String headImgUrl;

    @Excel(name = "性别", orderNum = "4")
    private String sexDesc;

    @Excel(name = "国家", orderNum = "5")
    private String country;

    @Excel(name = "省份", orderNum = "6")
    private String province;

    @Excel(name = "城市", orderNum = "7")
    private String city;

}
