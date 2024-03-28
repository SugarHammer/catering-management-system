package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 单位(Unit)实体类
 *
 * @author Hernie
 * @since 2021-09-12 17:09:09
 */

@ApiModel(value = "Unit", description = "单位")
public class Unit implements Serializable {
    private static final long serialVersionUID = 195151145095574131L;
    /**
     * 单位主键
     */
    @ApiModelProperty(name = "id", notes = "单位主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 单位名称
     */
    @ApiModelProperty(name = "unitName", notes = "单位名称", dataType = "String", required = true)
    private String unitName;
    /**
     * 单位类型（0代表整数，1代表小数）
     */
    @ApiModelProperty(name = "unitType", notes = "单位类型（0代表整数，1代表小数）", dataType = "Integer", required = true)
    private Integer unitType;
    /**
     * 点菜默认量
     */
    @ApiModelProperty(name = "unitDefaultAmount", notes = "点菜默认量", dataType = "Double", required = true)
    private Double unitDefaultAmount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Double getUnitDefaultAmount() {
        return unitDefaultAmount;
    }

    public void setUnitDefaultAmount(Double unitDefaultAmount) {
        this.unitDefaultAmount = unitDefaultAmount;
    }

}
