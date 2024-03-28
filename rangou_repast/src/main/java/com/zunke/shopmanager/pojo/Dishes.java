package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 菜品(Dishes)实体类
 *
 * @author Hernie
 * @since 2021-09-12 17:33:57
 */

@ApiModel(value = "Dishes", description = "菜品")
public class Dishes implements Serializable {
    private static final long serialVersionUID = 391030083167440824L;
    /**
     * 菜品主键
     */
    @ApiModelProperty(name = "id", notes = "菜品主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 菜品编号
     */
    @ApiModelProperty(name = "dishesNo", notes = "菜品编号", dataType = "Integer", required = true)
    private String dishesNo;
    /**
     * 菜品名称
     */
    @ApiModelProperty(name = "dishesName", notes = "菜品名称", dataType = "String", required = true)
    private String dishesName;
    /**
     * 菜品规格(0代表小份，1代表大份)
     */
    @ApiModelProperty(name = "dishesBulk", notes = "菜品规格(0代表小份，1代表大份)", dataType = "Integer", required = true)
    private Integer dishesBulk;
    /**
     * 菜品拼音简码
     */
    @ApiModelProperty(name = "dishesShortCode", notes = "菜品拼音简码", dataType = "String", required = true)
    private String dishesShortCode;
    /**
     * 菜品价格
     */
    @ApiModelProperty(name = "dishesPrice", notes = "菜品价格", dataType = "Double", required = true)
    private Double dishesPrice;
    /**
     * 菜品图片
     */
    @ApiModelProperty(name = "dishesPhoto", notes = "菜品图片", dataType = "String", required = true)
    private String dishesPhoto;
    /**
     * 菜品状态
     */
    @ApiModelProperty(name = "action", notes = "菜品状态", dataType = "String", required = true)
    private Integer dishesAction;
    /**
     * 单位主键
     */
    @ApiModelProperty(name = "unitId", notes = "单位主键", dataType = "Integer", required = true)
    private Integer unitId;
    /**
     * 菜品小类主键
     */
    @ApiModelProperty(name = "subcategoriesId", notes = "菜品小类主键", dataType = "Integer", required = true)
    private Integer subcategoriesId;

    public Integer getDishesAction() {
        return dishesAction;
    }

    public void setDishesAction(Integer dishesAction) {
        this.dishesAction = dishesAction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishesNo() {
        return dishesNo;
    }

    public void setDishesNo(String dishesNo) {
        this.dishesNo = dishesNo;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public Integer getDishesBulk() {
        return dishesBulk;
    }

    public void setDishesBulk(Integer dishesBulk) {
        this.dishesBulk = dishesBulk;
    }

    public String getDishesShortCode() {
        return dishesShortCode;
    }

    public void setDishesShortCode(String dishesShortCode) {
        this.dishesShortCode = dishesShortCode;
    }

    public Double getDishesPrice() {
        return dishesPrice;
    }

    public void setDishesPrice(Double dishesPrice) {
        this.dishesPrice = dishesPrice;
    }

    public String getDishesPhoto() {
        return dishesPhoto;
    }

    public void setDishesPhoto(String dishesPhoto) {
        this.dishesPhoto = dishesPhoto;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getSubcategoriesId() {
        return subcategoriesId;
    }

    public void setSubcategoriesId(Integer subcategoriesId) {
        this.subcategoriesId = subcategoriesId;
    }

}
