package top.longmarch.douyin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("douyin_account")
public class DouyinAccount {

    @TableId
    private String openId;
    private String unionId;
    private String nickname;
    private String source;
    private String location;
    private String avatar;
    private String avatarLarger;
    private Integer gender;
    private String genderStr;
    private String country;
    private String province;
    private String city;
    private String eAccountRole;
    private String scope;
    private Integer expireIn;
    private String accessToken;
    private String refreshToken;
    private Integer fansNum;
    private Integer followingNum;
    private Integer likeNum;
    private Integer commentNum;
    private Integer shareNum;
    private Integer profileNum;
    private Integer videoNum;
    private Integer videoPlayNum;
    private Integer defaultAccount;
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
