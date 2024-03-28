package com.zunke.shopmanager.pojo;

import java.util.Date;
import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 会员注册登录的实体类
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-21 1:13
 */

@ApiModel(value = "Mumber", description = "会员表")
public class Mumber implements Serializable {
    private static final long serialVersionUID = -24250742635706898L;
    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", notes = "主键id", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 会员名
     */
    @ApiModelProperty(name = "mumberName", notes = "会员名", dataType = "String", required = true)
    private String mumberName;
    /**
     * 会员电话号码
     */
    @ApiModelProperty(name = "mumberPhone", notes = "会员电话号码", dataType = "String", required = true)
    private String mumberPhone;
    /**
     * 用户密码
     */
    @ApiModelProperty(name = "mumberPassword", notes = "用户密码", dataType = "String", required = true)
    private String mumberPassword;
    /**
     * 用户生日
     */
    @ApiModelProperty(name = "mumberBirthday", notes = "用户生日", dataType = "Date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mumberBirthday;
    /**
     * 用户余额
     */
    @ApiModelProperty(name = "mumberBalance", notes = "用户余额", dataType = "Double", required = true)
    private Double mumberBalance;
    /**
     * 用户余额
     */
    @ApiModelProperty(name = "mumberTotalCost", notes = "用户余额", dataType = "Double", required = true)
    private Double mumberTotalCost;
    /**
     * 会员积分
     */
    @ApiModelProperty(name = "mumberIntegral", notes = "会员积分", dataType = "Integer", required = true)
    private Integer mumberIntegral;
    /**
     * 会员等级
     */
    @ApiModelProperty(name = "mumberLevelId", notes = "会员等级", dataType = "Integer", required = true)
    private Integer mumberLevelId;
    /**
     * 会员状态(默认为1,0表示禁用)
     */
    @ApiModelProperty(name = "mumberAction", notes = "会员状态(默认为1,0表示禁用)", dataType = "Integer", required = true)
    private Integer mumberAction;
    /**
     * 会员注册时间
     */
    @ApiModelProperty(name = "mumberRergistrationTime", notes = "会员注册时间", dataType = "Date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mumberRergistrationTime;
    /**
     * 会员头像
     */
    @ApiModelProperty(name = "mumberHeadImg", notes = "会员头像", dataType = "String", required = true)
    private String mumberHeadImg;
    /**
     * 会员uuid
     */
    @ApiModelProperty(name = "mumberUuid", notes = "会员uuid", dataType = "String", required = true)
    private String mumberUuid;
    /**
     * 用户角色
     */
    @ApiModelProperty(name = "roleId", notes = "用户角色", dataType = "Integer", required = true)
    private Integer roleId;
    /**
     * 手机验证码
     */
    @ApiModelProperty(name = "phoneCode", notes = "手机验证码", dataType = "Integer", required = true)
    private String phoneCode;
    /**
     * 验证码发送时间
     */
    @ApiModelProperty(name = "sendTime", notes = "验证码发送时间", dataType = "Date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    private MumberLevel mumberLevel;

    private String mumberEmail;

    public String getMumberEmail() {
        return mumberEmail;
    }

    public void setMumberEmail(String mumberEmail) {
        this.mumberEmail = mumberEmail;
    }

    public String getMumberName() {
        return mumberName;
    }

    public void setMumberName(String mumberName) {
        this.mumberName = mumberName;
    }

    public MumberLevel getMumberLevel() {
        return mumberLevel;
    }

    public void setMumberLevel(MumberLevel mumberLevel) {
        this.mumberLevel = mumberLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMumberPhone() {
        return mumberPhone;
    }

    public void setMumberPhone(String mumberPhone) {
        this.mumberPhone = mumberPhone;
    }

    public String getMumberPassword() {
        return mumberPassword;
    }

    public void setMumberPassword(String mumberPassword) {
        this.mumberPassword = mumberPassword;
    }

    public Date getMumberBirthday() {
        return mumberBirthday;
    }

    public void setMumberBirthday(Date mumberBirthday) {
        this.mumberBirthday = mumberBirthday;
    }

    public Double getMumberBalance() {
        return mumberBalance;
    }

    public void setMumberBalance(Double mumberBalance) {
        this.mumberBalance = mumberBalance;
    }

    public Double getMumberTotalCost() {
        return mumberTotalCost;
    }

    public void setMumberTotalCost(Double mumberTotalCost) {
        this.mumberTotalCost = mumberTotalCost;
    }

    public Integer getMumberIntegral() {
        return mumberIntegral;
    }

    public void setMumberIntegral(Integer mumberIntegral) {
        this.mumberIntegral = mumberIntegral;
    }

    public Integer getLevelId() {
        return mumberLevelId;
    }

    public void setLevelId(Integer mumberLevelId) {
        this.mumberLevelId = mumberLevelId;
    }

    public Integer getMumberAction() {
        return mumberAction;
    }

    public void setMumberAction(Integer mumberAction) {
        this.mumberAction = mumberAction;
    }

    public Date getMumberRergistrationTime() {
        return mumberRergistrationTime;
    }

    public void setMumberRergistrationTime(Date mumberRergistrationTime) {
        this.mumberRergistrationTime = mumberRergistrationTime;
    }

    public String getMumberHeadImg() {
        return mumberHeadImg;
    }

    public void setMumberHeadImg(String mumberHeadImg) {
        this.mumberHeadImg = mumberHeadImg;
    }

    public String getMumberUuid() {
        return mumberUuid;
    }

    public void setMumberUuid(String mumberUuid) {
        this.mumberUuid = mumberUuid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "{\"Mumber\":{"
                + "\"id\":"
                + id
                + ",\"mumberName\":\""
                + mumberName + '\"'
                + ",\"mumberPhone\":\""
                + mumberPhone + '\"'
                + ",\"mumberPassword\":\""
                + mumberPassword + '\"'
                + ",\"mumberBirthday\":\""
                + mumberBirthday + '\"'
                + ",\"mumberBalance\":"
                + mumberBalance
                + ",\"mumberTotalCost\":"
                + mumberTotalCost
                + ",\"mumberIntegral\":"
                + mumberIntegral
                + ",\"mumberLevelId\":"
                + mumberLevelId
                + ",\"mumberAction\":"
                + mumberAction
                + ",\"mumberRergistrationTime\":\""
                + mumberRergistrationTime + '\"'
                + ",\"mumberHeadImg\":\""
                + mumberHeadImg + '\"'
                + ",\"mumberUuid\":\""
                + mumberUuid + '\"'
                + ",\"roleId\":"
                + roleId
                + ",\"phoneCode\":"
                + phoneCode
                + ",\"sendTime\":\""
                + sendTime + '\"'
                + "}}";

    }

}
