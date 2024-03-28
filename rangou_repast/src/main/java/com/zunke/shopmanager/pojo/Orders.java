package com.zunke.shopmanager.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表(Orders)实体类
 *
 * @author makejava
 * @since 2021-09-16 10:07:53
 * @version 1.0
 */
@ApiModel(value = "Orders",description = "订单表")
public class Orders implements Serializable {
    private static final long serialVersionUID = -58187375518685370L;
    /**
    * 订单id
    */
	@ApiModelProperty(name = "id",notes = "订单id",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 会员id
    */
	@ApiModelProperty(name = "mumberId",notes = "会员id",dataType = "Integer",required = true)
    private Integer mumberId;
    /**
    * 订单编号
    */
	@ApiModelProperty(name = "ordersNo",notes = "订单编号",dataType = "String",required = true)
    private String ordersNo;
    /**
    * 创建时间
    */
	@ApiModelProperty(name = "createDate",notes = "创建时间",dataType = "Date",required = true)
    // 接收字符串日期，转换为date对象，前端必须传递 "2020-06-20 01:01:01"  这样的格式
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
// 下面是服务器响应返回的内容，如果没有格式化转换，那么返回的内容是长毫秒数，接收mysql数据库中的数据也需要设置东八区+8时
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    /**
    * 支付时间
    */
	@ApiModelProperty(name = "payDate",notes = "支付时间",dataType = "Date",required = true)
    // 接收字符串日期，转换为date对象，前端必须传递 "2020-06-20 01:01:01"  这样的格式
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    // 下面是服务器响应返回的内容，如果没有格式化转换，那么返回的内容是长毫秒数，接收mysql数据库中的数据也需要设置东八区+8时
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payDate;
    /**
    * 备注
    */
	@ApiModelProperty(name = "info",notes = "备注",dataType = "String",required = true)
    private String info;
    /**
    * 是否删除
    */
	@ApiModelProperty(name = "isDel",notes = "是否删除",dataType = "Integer",required = true)
    private Integer isDel;
    /**
    * 总价
    */
	@ApiModelProperty(name = "totalPrice",notes = "总价",dataType = "Double",required = true)
    private Double totalPrice;
    /**
    * 餐桌id
    */
	@ApiModelProperty(name = "diningId",notes = "餐桌id",dataType = "Integer",required = true)
    private Integer diningId;
    /**
    * 积分优惠
    */
	@ApiModelProperty(name = "discount",notes = "积分优惠",dataType = "Double",required = true)
    private Double discount;
    @ApiModelProperty(name = "cartId",notes = "购物车id",dataType = "Integer",required = true)
	private Integer cartId;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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
        
    public String getOrdersNo() {
        return ordersNo;
    }

    public void setOrdersNo(String ordersNo) {
        this.ordersNo = ordersNo;
    }
        
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
        
    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
        
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
        
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
        
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
        
    public Integer getDiningId() {
        return diningId;
    }

    public void setDiningId(Integer diningId) {
        this.diningId = diningId;
    }
        
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "{\"Orders\":{"
                + "\"id\":"
                + id
                + ",\"mumberId\":"
                + mumberId
                + ",\"ordersNo\":\""
                + ordersNo + '\"'
                + ",\"createDate\":\""
                + createDate + '\"'
                + ",\"payDate\":\""
                + payDate + '\"'
                + ",\"info\":\""
                + info + '\"'
                + ",\"isDel\":"
                + isDel
                + ",\"totalPrice\":"
                + totalPrice
                + ",\"diningId\":"
                + diningId
                + ",\"discount\":"
                + discount
                + "}}";

    }
}