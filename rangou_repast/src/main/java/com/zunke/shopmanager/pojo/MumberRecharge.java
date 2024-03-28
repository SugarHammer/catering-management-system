package com.zunke.shopmanager.pojo;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 会员充值表(MumberRecharge)实体类
 *
 * @author hy
 * @since 2021-09-17 08:50:23
 */

@ApiModel(value = "MumberRecharge", description = "会员充值表")
public class MumberRecharge implements Serializable {
    private static final long serialVersionUID = 750968954639516876L;
    /**
     * 主键id
     */
    @ApiModelProperty(name = "id", notes = "主键id", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 会员id
     */
    @ApiModelProperty(name = "mumberId", notes = "会员id", dataType = "Integer", required = true)
    private Integer mumberId;
    /**
     * 会员交易订单号
     */
    @ApiModelProperty(name = "mumberRechargeOrderNo", notes = "会员交易订单号", dataType = "Integer", required = true)
    private Integer mumberRechargeOrderNo;
    /**
     * 会员充值金额
     */
    @ApiModelProperty(name = "mumberRechargeMoney", notes = "会员充值金额", dataType = "Integer", required = true)
    private Integer mumberRechargeMoney;
    /**
     * 会员充值时间
     */
    @ApiModelProperty(name = "mumberRechargeTime", notes = "会员充值时间", dataType = "Date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mumberRechargeTime;

    private Mumber mumber;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Mumber getMumber() {
        return mumber;
    }

    public void setMumber(Mumber mumber) {
        this.mumber = mumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMumberId() {
        return mumberId;
    }

    public void setMumberId(Integer mumberId) {
        this.mumberId = mumberId;
    }

    public Integer getMumberRechargeOrderNo() {
        return mumberRechargeOrderNo;
    }

    public void setMumberRechargeOrderNo(Integer mumberRechargeOrderNo) {
        this.mumberRechargeOrderNo = mumberRechargeOrderNo;
    }

    public Integer getMumberRechargeMoney() {
        return mumberRechargeMoney;
    }

    public void setMumberRechargeMoney(Integer mumberRechargeMoney) {
        this.mumberRechargeMoney = mumberRechargeMoney;
    }

    public Date getMumberRechargeTime() {
        return mumberRechargeTime;
    }

    public void setMumberRechargeTime(Date mumberRechargeTime) {
        this.mumberRechargeTime = mumberRechargeTime;
    }

}
