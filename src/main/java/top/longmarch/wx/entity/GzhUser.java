package top.longmarch.wx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 粉丝表
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
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
    @TableField("tag_ids")
    private String tagIds;

    @ApiModelProperty(value = "分维标签")
    @TableField("fen_wei_tags")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGzhId() {
        return gzhId;
    }

    public void setGzhId(Long gzhId) {
        this.gzhId = gzhId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNicknameTxt() {
        return nicknameTxt;
    }

    public void setNicknameTxt(String nicknameTxt) {
        this.nicknameTxt = nicknameTxt;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }
    public Integer getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Integer subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }
    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }
    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }
    public String getFenWeiTags() {
        return fenWeiTags;
    }

    public void setFenWeiTags(String fenWeiTags) {
        this.fenWeiTags = fenWeiTags;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getQrScene() {
        return qrScene;
    }

    public void setQrScene(String qrScene) {
        this.qrScene = qrScene;
    }
    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
    public String getJwid() {
        return jwid;
    }

    public void setJwid(String jwid) {
        this.jwid = jwid;
    }
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "GzhUser{" +
            "id=" + id +
            "gzhId=" + gzhId +
            ", openId=" + openId +
            ", nickname=" + nickname +
            ", nicknameTxt=" + nicknameTxt +
            ", remark=" + remark +
            ", headImgUrl=" + headImgUrl +
            ", sexDesc=" + sexDesc +
            ", sex=" + sex +
            ", subscribe=" + subscribe +
            ", subscribeTime=" + subscribeTime +
            ", subscribeScene=" + subscribeScene +
            ", mobile=" + mobile +
            ", bindStatus=" + bindStatus +
            ", bindTime=" + bindTime +
            ", tagIds=" + tagIds +
            ", fenWeiTags=" + fenWeiTags +
            ", province=" + province +
            ", city=" + city +
            ", country=" + country +
            ", qrScene=" + qrScene +
            ", qrSceneStr=" + qrSceneStr +
            ", groupId=" + groupId +
            ", language=" + language +
            ", unionId=" + unionId +
            ", jwid=" + jwid +
            ", deleted=" + deleted +
            ", tenantId=" + tenantId +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", updateBy=" + updateBy +
            ", updateTime=" + updateTime +
        "}";
    }
}
