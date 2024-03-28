package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 做法(Recipe)实体类
 *
 * @author Hernie
 * @since 2021-09-12 17:16:29
 */

@ApiModel(value = "Recipe", description = "做法")
public class Recipe implements Serializable {
    private static final long serialVersionUID = -93435393927850361L;
    /**
     * 做法主键
     */
    @ApiModelProperty(name = "id", notes = "做法主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 做法编号
     */
    @ApiModelProperty(name = "recipeNo", notes = "做法编号", dataType = "Integer", required = true)
    private Integer recipeNo;
    /**
     * 做法说明
     */
    @ApiModelProperty(name = "recipeExplain", notes = "做法说明", dataType = "String", required = true)
    private String recipeExplain;
    /**
     * 做法类型
     */
    @ApiModelProperty(name = "recipeType", notes = "做法类型", dataType = "String", required = true)
    private String recipeType;
    /**
     * 加价
     */
    @ApiModelProperty(name = "recipeMarkup", notes = "加价", dataType = "Double", required = true)
    private Double recipeMarkup;
    /**
     * 是否按量加价（0代表否，1代表是）
     */
    @ApiModelProperty(name = "recipeMarkupByAmount", notes = "是否按量加价（0代表否，1代表是）", dataType = "Integer", required = true)
    private Integer recipeMarkupByAmount;
    /**
     * 做法的拼音简码
     */
    @ApiModelProperty(name = "recipeShortCode", notes = "做法的拼音简码", dataType = "String", required = true)
    private String recipeShortCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipeNo() {
        return recipeNo;
    }

    public void setRecipeNo(Integer recipeNo) {
        this.recipeNo = recipeNo;
    }

    public String getRecipeExplain() {
        return recipeExplain;
    }

    public void setRecipeExplain(String recipeExplain) {
        this.recipeExplain = recipeExplain;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public Double getRecipeMarkup() {
        return recipeMarkup;
    }

    public void setRecipeMarkup(Double recipeMarkup) {
        this.recipeMarkup = recipeMarkup;
    }

    public Integer getRecipeMarkupByAmount() {
        return recipeMarkupByAmount;
    }

    public void setRecipeMarkupByAmount(Integer recipeMarkupByAmount) {
        this.recipeMarkupByAmount = recipeMarkupByAmount;
    }

    public String getRecipeShortCode() {
        return recipeShortCode;
    }

    public void setRecipeShortCode(String recipeShortCode) {
        this.recipeShortCode = recipeShortCode;
    }

}
