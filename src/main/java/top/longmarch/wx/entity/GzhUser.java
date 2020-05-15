package top.longmarch.wx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 粉丝表
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
@Data
@TableName("wx_gzh_user")
@ApiModel(value="GzhUser对象", description="粉丝表")
public class GzhUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公众号ID")
    @TableField("gzh_id")
    private Long gzhId;

    @ApiModelProperty(value = "openid")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "过滤后昵称")
    @TableField("nickname_txt")
    private String nicknameTxt;

    @ApiModelProperty(value = "备注名称")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "用户头像")
    @TableField("head_img_url")
    private String headImgUrl;

    @ApiModelProperty(value = "性别")
    @TableField("sex_desc")
    private String sexDesc;

    @ApiModelProperty(value = "性别：'1':男性；'2':女性；'0':未知")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "是否关注:'0':未关注；'1':关注")
    @TableField("subscribe")
    private Integer subscribe;

    @ApiModelProperty(value = "关注时间")
    @TableField("subscribe_time")
    private Integer subscribeTime;

    @ApiModelProperty(value = "用户关注渠道来源")
    @TableField("subscribe_scene")
    private String subscribeScene;

    @ApiModelProperty(value = "手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "绑定状态：'N':未绑定；'Y':已绑定；'V':绑定中")
    @TableField("bind_status")
    private String bindStatus;

    @ApiModelProperty(value = "绑定时间")
    @TableField("bind_time")
    private Date bindTime;

    @ApiModelProperty(value = "标签id")
    @TableField(value = "tag_ids", updateStrategy = FieldStrategy.IGNORED)
    private String tagIds;

    @ApiModelProperty(value = "分维标签")
    @TableField(value = "fen_wei_tags", updateStrategy = FieldStrategy.IGNORED)
    private String fenWeiTags;

    @ApiModelProperty(value = "省份")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "城市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "地区")
    @TableField("country")
    private String country;

    @ApiModelProperty(value = "二维码扫码场景")
    @TableField("qr_scene")
    private String qrScene;

    @ApiModelProperty(value = "二维码扫码常见描述")
    @TableField("qr_scene_str")
    private String qrSceneStr;

    @ApiModelProperty(value = "用户所在分组id")
    @TableField("group_id")
    private Integer groupId;

    @ApiModelProperty(value = "用户的语言，简体中文为zh_CN")
    @TableField("language")
    private String language;

    @TableField("union_id")
    private String unionId;

    @ApiModelProperty(value = "公众号原始id")
    @TableField("jwid")
    private String jwid;

    @ApiModelProperty(value = "是否删除（1 已删除，0 未删除，默 0）")
    @TableField("is_deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "租户编号")
    @TableField("tenant_id")
    private Long tenantId;

    @ApiModelProperty(value = "创建者ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新ID")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
