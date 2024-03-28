package com.zunke.shopmanager.pojo;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 积分交易表(Integral)实体类
 *
 * @author hy
 * @since 2021-09-16 16:39:37
 */

@ApiModel(value = "Integral", description = "积分交易表")
public class Integral implements Serializable {
    private static final long serialVersionUID = 212302827128450475L;
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
     * 积分总额
     */
    @ApiModelProperty(name = "integralAmount", notes = "积分总额", dataType = "Integer", required = true)
    private Integer integralAmount;
    /**
     * 积分交易时间
     */
    @ApiModelProperty(name = "integralTradingHour", notes = "积分交易时间", dataType = "Date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date integralTradingHour;
    /**
     * 交易订单号
     */
    @ApiModelProperty(name = "ordersId", notes = "交易订单号", dataType = "Integer", required = true)
    private Integer ordersId;
    /**
     * 交易数据状态
     */
    @ApiModelProperty(name = "integralAction", notes = "交易数据状态", dataType = "Integer", required = true)
    private Integer integralAction;

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

    public Integer getIntegralAmount() {
        return integralAmount;
    }

    public void setIntegralAmount(Integer integralAmount) {
        this.integralAmount = integralAmount;
    }

    public Date getIntegralTradingHour() {
        return integralTradingHour;
    }

    public void setIntegralTradingHour(Date integralTradingHour) {
        this.integralTradingHour = integralTradingHour;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getIntegralAction() {
        return integralAction;
    }

    public void setIntegralAction(Integer integralAction) {
        this.integralAction = integralAction;
    }

}
