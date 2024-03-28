package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 菜品小类(Subcategories)实体类
 *
 * @author Hernie
 * @since 2021-09-13 14:00:16
 */

@ApiModel(value = "Subcategories", description = "菜品小类")
public class Subcategories implements Serializable {
    private static final long serialVersionUID = -44205571582590335L;
    /**
     * 菜品小类主键
     */
    @ApiModelProperty(name = "id", notes = "菜品小类主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 菜品小类编号
     */
    @ApiModelProperty(name = "subcategoriesNo", notes = "菜品小类编号", dataType = "String", required = true)
    private String subcategoriesNo;
    /**
     * 菜品小类名称
     */
    @ApiModelProperty(name = "subcategoriesName", notes = "菜品小类名称", dataType = "String", required = true)
    private String subcategoriesName;
    /**
     * 菜品小类颜色
     */
    @ApiModelProperty(name = "subcategoriesColor", notes = "菜品小类颜色", dataType = "String", required = true)
    private String subcategoriesColor;
    /**
     * 菜品的上菜顺序（1-5，数字越小，优先级越高）
     */
    @ApiModelProperty(name = "subcategoriesOrder", notes = "菜品的上菜顺序（1-5，数字越小，优先级越高）", dataType = "Integer", required = true)
    private Integer subcategoriesOrder;
    /**
     * 菜品大类主键
     */
    @ApiModelProperty(name = "categoriesId", notes = "菜品大类主键", dataType = "Integer", required = true)
    private Integer categoriesId;

    private Categories categories;

    private List<Dishes> dishesList;

    public List<Dishes> getDishesList() {
        return dishesList;
    }

    public void setDishesList(List<Dishes> dishesList) {
        this.dishesList = dishesList;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcategoriesNo() {
        return subcategoriesNo;
    }

    public void setSubcategoriesNo(String subcategoriesNo) {
        this.subcategoriesNo = subcategoriesNo;
    }

    public String getSubcategoriesName() {
        return subcategoriesName;
    }

    public void setSubcategoriesName(String subcategoriesName) {
        this.subcategoriesName = subcategoriesName;
    }

    public String getSubcategoriesColor() {
        return subcategoriesColor;
    }

    public void setSubcategoriesColor(String subcategoriesColor) {
        this.subcategoriesColor = subcategoriesColor;
    }

    public Integer getSubcategoriesOrder() {
        return subcategoriesOrder;
    }

    public void setSubcategoriesOrder(Integer subcategoriesOrder) {
        this.subcategoriesOrder = subcategoriesOrder;
    }

    public Integer getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Integer categoriesId) {
        this.categoriesId = categoriesId;
    }

}
