package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 套餐信息表(ComboInfo)实体类
 *
 * @author makejava
 * @since 2021-09-16 10:13:08
 * @version 1.0
 */
@ApiModel(value = "ComboInfo",description = "套餐信息表")
public class ComboInfo implements Serializable {
    private static final long serialVersionUID = 837651394282302099L;
    /**
    * 套餐信息id
    */
	@ApiModelProperty(name = "id",notes = "套餐信息id",dataType = "Integer",required = true)
    private Integer id;
    /**
    * 套餐id
    */
	@ApiModelProperty(name = "comboId",notes = "套餐id",dataType = "Integer",required = true)
    private Integer comboId;
    /**
    * 套餐数量
    */
	@ApiModelProperty(name = "comboNum",notes = "套餐数量",dataType = "Integer",required = true)
    private Integer comboNum;
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
        
    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }
        
    public Integer getComboNum() {
        return comboNum;
    }

    public void setComboNum(Integer comboNum) {
        this.comboNum = comboNum;
    }
        
    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

}