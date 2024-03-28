package com.zunke.shopmanager.dto;

import com.zunke.shopmanager.pojo.MumberLevel;

import java.util.Date;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-21 11:20
 */
public class MumberDTO {

    private Integer id;
    private String mumberName;
    private String mumberPhone;
    private String mumberPassword;
    private Date mumberBirthday;
    private Double mumberBalance;
    private Double mumberTotalCost;
    private Integer mumberIntegral;
    private Integer mumberLevelId;
    private Integer mumberAction;
    private Date mumberRergistrationTime;
    private String mumberHeadImg;
    private String mumberUuid;
    private Integer roleId;
    private String phoneCode;
    private Date sendTime;
    private String mumberEmail;
    private MumberLevel mumberLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMumberName() {
        return mumberName;
    }

    public void setMumberName(String mumberName) {
        this.mumberName = mumberName;
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

    public Integer getMumberLevelId() {
        return mumberLevelId;
    }

    public void setMumberLevelId(Integer mumberLevelId) {
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

    public String getMumberEmail() {
        return mumberEmail;
    }

    public void setMumberEmail(String mumberEmail) {
        this.mumberEmail = mumberEmail;
    }

    public MumberLevel getMumberLevel() {
        return mumberLevel;
    }

    public void setMumberLevel(MumberLevel mumberLevel) {
        this.mumberLevel = mumberLevel;
    }

    @Override
    public String toString() {
        return "MumberDTO{" +
                "id=" + id +
                ", mumberName='" + mumberName + '\'' +
                ", mumberPhone='" + mumberPhone + '\'' +
                ", mumberPassword='" + mumberPassword + '\'' +
                ", mumberBirthday=" + mumberBirthday +
                ", mumberBalance=" + mumberBalance +
                ", mumberTotalCost=" + mumberTotalCost +
                ", mumberIntegral=" + mumberIntegral +
                ", mumberLevelId=" + mumberLevelId +
                ", mumberAction=" + mumberAction +
                ", mumberRergistrationTime=" + mumberRergistrationTime +
                ", mumberHeadImg='" + mumberHeadImg + '\'' +
                ", mumberUuid='" + mumberUuid + '\'' +
                ", roleId=" + roleId +
                ", phoneCode='" + phoneCode + '\'' +
                ", sendTime=" + sendTime +
                ", mumberEmail='" + mumberEmail + '\'' +
                ", mumberLevel=" + mumberLevel +
                '}';
    }
}
