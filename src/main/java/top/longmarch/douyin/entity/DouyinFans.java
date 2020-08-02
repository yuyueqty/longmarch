package top.longmarch.douyin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_UUID;

@Data
@TableName("douyin_fans")
public class DouyinFans {

    @TableId(type = ASSIGN_UUID)
    private String openId;
    private String unionId;
    private String nickname;
    private String avatar;
    private Integer gender;
    private String genderStr;
    private String country;
    private String province;
    private String city;
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
