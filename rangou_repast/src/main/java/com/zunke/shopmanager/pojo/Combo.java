package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 套菜(Combo)实体类
 *
 * @author Hernie
 * @since 2021-09-12 17:16:28
 */

@ApiModel(value = "Combo", description = "套菜")
public class Combo implements Serializable {
    private static final long serialVersionUID = 472245546953464603L;
    /**
     * 套菜主键
     */
    @ApiModelProperty(name = "id", notes = "套菜主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 套菜编号
     */
    @ApiModelProperty(name = "comboNo", notes = "套菜编号", dataType = "Integer", required = true)
    private Integer comboNo;
    /**
     * 套菜价格
     */
    @ApiModelProperty(name = "comboPrice", notes = "套菜价格", dataType = "Double", required = true)
    private Double comboPrice;
    /**
     * 单位主键
     */
    @ApiModelProperty(name = "unitId", notes = "单位主键", dataType = "Integer", required = true)
    private Integer unitId;
    /**
     * 套菜名称
     */
    @ApiModelProperty(name = "comboName", notes = "套菜名称", dataType = "String", required = true)
    private String comboName;

    private String comboPhoto;

    private Unit unit;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComboNo() {
        return comboNo;
    }

    public void setComboNo(Integer comboNo) {
        this.comboNo = comboNo;
    }

    public Double getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(Double comboPrice) {
        this.comboPrice = comboPrice;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getComboPhoto() {
        return comboPhoto;
    }

    public void setComboPhoto(String comboPhoto) {
        this.comboPhoto = comboPhoto;
    }
}
