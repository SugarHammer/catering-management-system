package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 菜品信息表(DishesInfo)实体类
 *
 * @author makejava
 * @since 2021-09-16 10:08:27
 * @version 1.0
 */
@ApiModel(value = "DishesInfo",description = "菜品信息表")
public class DishesInfo implements Serializable {
    private static final long serialVersionUID = 386004554647585070L;
    /**
    * 菜品信息id
    */
	@ApiModelProperty(name = "id",notes = "菜品信息id",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 菜品id
    */
	@ApiModelProperty(name = "dishesId",notes = "菜品id",dataType = "Integer",required = true)
    private Integer dishesId;
    /**
    * 数量
    */
	@ApiModelProperty(name = "dishesNum",notes = "数量",dataType = "Integer",required = true)
    private Integer dishesNum;
    /**
    * 订单id
    */
	@ApiModelProperty(name = "ordersId",notes = "订单id",dataType = "Integer",required = true)
    private Integer ordersId;

        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
        
    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }
        
    public Integer getDishesNum() {
        return dishesNum;
    }

    public void setDishesNum(Integer dishesNum) {
        this.dishesNum = dishesNum;
    }
        
    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

}