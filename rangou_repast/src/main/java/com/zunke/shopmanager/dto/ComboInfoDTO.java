package com.zunke.shopmanager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuzhe
 * @version 1.0
 * @since 2021-09-16-16:11
 */
public class ComboInfoDTO implements Serializable {

    private static final long serialVersionUID = -3698076025710321835L;
    /**
     * 套餐信息id
     */
    @ApiModelProperty(name = "id",notes = "套餐信息id",dataType = "Integer",required = true)
    private Integer id;
    /**
     * 套菜名称
     */
    @ApiModelProperty(name = "comboName",notes = "套菜名称",dataType = "String",required = true)
    private String comboName;
    /**
     * 套菜价格
     */
    @ApiModelProperty(name = "comboPrice",notes = "套菜价格",dataType = "Double",required = true)
    private Double comboPrice;
    /**
     * 套餐数量
     */
    @ApiModelProperty(name = "comboNum",notes = "套餐数量",dataType = "Integer",required = true)
    private Integer comboNum;

    public Integer getId() {
        return id;
    }

    public ComboInfoDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getComboName() {
        return comboName;
    }

    public ComboInfoDTO setComboName(String comboName) {
        this.comboName = comboName;
        return this;
    }

    public Double getComboPrice() {
        return comboPrice;
    }

    public ComboInfoDTO setComboPrice(Double comboPrice) {
        this.comboPrice = comboPrice;
        return this;
    }

    public Integer getComboNum() {
        return comboNum;
    }

    public ComboInfoDTO setComboNum(Integer comboNum) {
        this.comboNum = comboNum;
        return this;
    }
}
