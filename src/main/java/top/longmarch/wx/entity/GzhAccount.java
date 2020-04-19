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
 * 系统公众号表
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
@TableName("wx_gzh_account")
@ApiModel(value="GzhAccount对象", description="系统公众号表")
public class GzhAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公众号")
    @TableField("jwid")
    private String jwid;

    @ApiModelProperty(value = "名称")
    @TableField("wx_name")
    private String wxName;

    @ApiModelProperty(value = "公众号登录账号")
    @TableField("login_name")
    private String loginName;

    @ApiModelProperty(value = "公众号登录密码")
    @TableField("login_passwd")
    private String loginPasswd;

    @ApiModelProperty(value = "应用类型")
    @TableField("application_type")
    private String applicationType;

    @ApiModelProperty(value = "微信二维码图片")
    @TableField("qrcodeimg")
    private String qrcodeimg;

    @ApiModelProperty(value = "微信号")
    @TableField("weixin_number")
    private String weixinNumber;

    @ApiModelProperty(value = "微信AppId")
    @TableField("weixin_appid")
    private String weixinAppid;

    @ApiModelProperty(value = "微信AppSecret")
    @TableField("weixin_appsecret")
    private String weixinAppsecret;

    @ApiModelProperty(value = "分维AppId")
    @TableField("fw_appid")
    private String fwAppid;

    @ApiModelProperty(value = "分维AppSecret")
    @TableField("fw_appsecret")
    private String fwAppsecret;

    @ApiModelProperty(value = "计费类型")
    @TableField("fee_type")
    private Integer feeType;

    @ApiModelProperty(value = "分维解析行业")
    @TableField("fw_field")
    private Integer fwField;

    @ApiModelProperty(value = "公众号类型")
    @TableField("account_type")
    private Integer accountType;

    @ApiModelProperty(value = "是否认证")
    @TableField("auth_status")
    private Integer authStatus;

    @ApiModelProperty(value = "Access_Token")
    @TableField("access_token")
    private String accessToken;

    @ApiModelProperty(value = "token获取的时间")
    @TableField("token_gettime")
    private Date tokenGettime;

    @ApiModelProperty(value = "api凭证")
    @TableField("apiticket")
    private String apiticket;

    @ApiModelProperty(value = "apiticket获取时间")
    @TableField("apiticket_gettime")
    private Date apiticketGettime;

    @ApiModelProperty(value = "jsapiticket")
    @TableField("jsapiticket")
    private String jsapiticket;

    @ApiModelProperty(value = "jsapiticket获取时间")
    @TableField("jsapiticket_gettime")
    private Date jsapiticketGettime;

    @ApiModelProperty(value = "类型：1手动授权，2扫码授权")
    @TableField("auth_type")
    private Boolean authType;

    @ApiModelProperty(value = "功能的开通状况：1代表已开通")
    @TableField("business_info")
    private String businessInfo;

    @ApiModelProperty(value = "公众号授权给开发者的权限集")
    @TableField("func_info")
    private String funcInfo;

    @ApiModelProperty(value = "授权方昵称")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "授权方头像")
    @TableField("headimgurl")
    private String headimgurl;

    @ApiModelProperty(value = "授权信息")
    @TableField("authorization_info")
    private String authorizationInfo;

    @ApiModelProperty(value = "刷新token")
    @TableField("authorizer_refresh_token")
    private String authorizerRefreshToken;

    @ApiModelProperty(value = "令牌")
    @TableField("token")
    private String token;

    @ApiModelProperty(value = "授权状态（1正常，2已取消）")
    @TableField("authorization_status")
    private String authorizationStatus;

    @ApiModelProperty(value = "是否删除（1 已删除，0 未删除，默 0）")
    @TableField("is_deleted")
    private Integer deleted;

    @ApiModelProperty(value = "是否默认账号（1 是，0 否，默 0）")
    @TableField("is_default_account")
    private Integer defaultAccount;

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
    public String getJwid() {
        return jwid;
    }

    public void setJwid(String jwid) {
        this.jwid = jwid;
    }
    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd;
    }
    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
    public String getQrcodeimg() {
        return qrcodeimg;
    }

    public void setQrcodeimg(String qrcodeimg) {
        this.qrcodeimg = qrcodeimg;
    }
    public String getWeixinNumber() {
        return weixinNumber;
    }

    public void setWeixinNumber(String weixinNumber) {
        this.weixinNumber = weixinNumber;
    }
    public String getWeixinAppid() {
        return weixinAppid;
    }

    public void setWeixinAppid(String weixinAppid) {
        this.weixinAppid = weixinAppid;
    }
    public String getWeixinAppsecret() {
        return weixinAppsecret;
    }

    public void setWeixinAppsecret(String weixinAppsecret) {
        this.weixinAppsecret = weixinAppsecret;
    }

    public String getFwAppid() {
        return fwAppid;
    }

    public void setFwAppid(String fwAppid) {
        this.fwAppid = fwAppid;
    }

    public String getFwAppsecret() {
        return fwAppsecret;
    }

    public void setFwAppsecret(String fwAppsecret) {
        this.fwAppsecret = fwAppsecret;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Integer getFwField() {
        return fwField;
    }

    public void setFwField(Integer fwField) {
        this.fwField = fwField;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Date getTokenGettime() {
        return tokenGettime;
    }

    public void setTokenGettime(Date tokenGettime) {
        this.tokenGettime = tokenGettime;
    }
    public String getApiticket() {
        return apiticket;
    }

    public void setApiticket(String apiticket) {
        this.apiticket = apiticket;
    }
    public Date getApiticketGettime() {
        return apiticketGettime;
    }

    public void setApiticketGettime(Date apiticketGettime) {
        this.apiticketGettime = apiticketGettime;
    }
    public String getJsapiticket() {
        return jsapiticket;
    }

    public void setJsapiticket(String jsapiticket) {
        this.jsapiticket = jsapiticket;
    }
    public Date getJsapiticketGettime() {
        return jsapiticketGettime;
    }

    public void setJsapiticketGettime(Date jsapiticketGettime) {
        this.jsapiticketGettime = jsapiticketGettime;
    }
    public Boolean getAuthType() {
        return authType;
    }

    public void setAuthType(Boolean authType) {
        this.authType = authType;
    }
    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }
    public String getFuncInfo() {
        return funcInfo;
    }

    public void setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    public String getAuthorizationInfo() {
        return authorizationInfo;
    }

    public void setAuthorizationInfo(String authorizationInfo) {
        this.authorizationInfo = authorizationInfo;
    }
    public String getAuthorizerRefreshToken() {
        return authorizerRefreshToken;
    }

    public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
        this.authorizerRefreshToken = authorizerRefreshToken;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(String authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(Integer defaultAccount) {
        this.defaultAccount = defaultAccount;
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
        return "GzhAccount{" +
            "id=" + id +
            ", jwid=" + jwid +
            ", wxName=" + wxName +
            ", loginName=" + loginName +
            ", loginPasswd=" + loginPasswd +
            ", applicationType=" + applicationType +
            ", qrcodeimg=" + qrcodeimg +
            ", weixinNumber=" + weixinNumber +
            ", weixinAppid=" + weixinAppid +
            ", weixinAppsecret=" + weixinAppsecret +
            ", fwAppid=" + fwAppid +
            ", fwAppsecret=" + fwAppsecret +
            ", feeType=" + feeType +
            ", fwField=" + fwField +
            ", accountType=" + accountType +
            ", authStatus=" + authStatus +
            ", accessToken=" + accessToken +
            ", tokenGettime=" + tokenGettime +
            ", apiticket=" + apiticket +
            ", feeType=" + feeType +
            ", apiticketGettime=" + apiticketGettime +
            ", jsapiticket=" + jsapiticket +
            ", jsapiticketGettime=" + jsapiticketGettime +
            ", authType=" + authType +
            ", businessInfo=" + businessInfo +
            ", funcInfo=" + funcInfo +
            ", nickName=" + nickName +
            ", headimgurl=" + headimgurl +
            ", authorizationInfo=" + authorizationInfo +
            ", authorizerRefreshToken=" + authorizerRefreshToken +
            ", token=" + token +
            ", authorizationStatus=" + authorizationStatus +
            ", deleted=" + deleted +
            ", defaultAccount=" + defaultAccount +
            ", tenantId=" + tenantId +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", updateBy=" + updateBy +
            ", updateTime=" + updateTime +
        "}";
    }
}
