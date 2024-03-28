package com.zunke.shopmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuzhe
 * @version 1.0
 * @since 2021-09-16-12:52
 */
public class OrderMumber implements Serializable {

    private static final long serialVersionUID = 7778901651019289534L;
    /**
     * 订单id
     */
    @ApiModelProperty(name = "id",notes = "订单id",dataType = "Integer",required = true)
    private Integer id;
    /**
     * 会员名称
     */
    @ApiModelProperty(name = "mumberName",notes = "会员名称",dataType = "String",required = true)
    private String mumberName;
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
     * 餐桌编号
     */
    @ApiModelProperty(name = "diningNumber",notes = "餐桌编号",dataType = "String",required = true)
    private String diningNumber;

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

    public Integer getId() {
        return id;
    }

    public OrderMumber setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMumberName() {
        return mumberName;
    }

    public OrderMumber setMumberName(String mumberName) {
        this.mumberName = mumberName;
        return this;
    }

    public String getOrdersNo() {
        return ordersNo;
    }

    public OrderMumber setOrdersNo(String ordersNo) {
        this.ordersNo = ordersNo;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public OrderMumber setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getPayDate() {
        return payDate;
    }

    public OrderMumber setPayDate(Date payDate) {
        this.payDate = payDate;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public OrderMumber setInfo(String info) {
        this.info = info;
        return this;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public OrderMumber setIsDel(Integer isDel) {
        this.isDel = isDel;
        return this;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public OrderMumber setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public String getDiningNumber() {
        return diningNumber;
    }

    public OrderMumber setDiningNumber(String diningNumber) {
        this.diningNumber = diningNumber;
        return this;
    }

    public Integer getDiningId() {
        return diningId;
    }

    public OrderMumber setDiningId(Integer diningId) {
        this.diningId = diningId;
        return this;
    }

    public Double getDiscount() {
        return discount;
    }

    public OrderMumber setDiscount(Double discount) {
        this.discount = discount;
        return this;
    }
}
