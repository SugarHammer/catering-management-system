package com.zunke.shopmanager.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 菜品做法(DishesRecipe)实体类
 *
 * @author Hernie
 * @since 2021-09-12 17:16:27
 */

@ApiModel(value = "DishesRecipe", description = "菜品做法")
public class DishesRecipe implements Serializable {
    private static final long serialVersionUID = -24708268046625485L;
    /**
     * 菜品做法主键
     */
    @ApiModelProperty(name = "id", notes = "菜品做法主键", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 菜品主键
     */
    @ApiModelProperty(name = "dishesId", notes = "菜品主键", dataType = "Integer", required = true)
    private Integer dishesId;
    /**
     * 做法主键
     */
    @ApiModelProperty(name = "recipeId", notes = "做法主键", dataType = "Integer", required = true)
    private Integer recipeId;


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

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

}
