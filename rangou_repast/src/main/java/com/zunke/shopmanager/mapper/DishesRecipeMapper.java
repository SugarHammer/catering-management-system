package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.DishesRecipe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜品做法(DishesRecipe)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:16:27
 */

public interface DishesRecipeMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<DishesRecipe> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DishesRecipe selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("name") String name);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<DishesRecipe> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param dishesRecipe 实例对象
     */
    void insert(DishesRecipe dishesRecipe);

    /**
     * 修改数据
     *
     * @param dishesRecipe 实例对象
     * @return 影响行数
     */
    int updateById(DishesRecipe dishesRecipe);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过菜品主键和做法主键删除
     *
     * @param recipeId 做法主键
     * @param dishesId 菜品主键
     * @return 影响行数
     */
    int deleteByDishesIdAndRecipeId(@Param("dishesId") String dishesId, @Param("recipeId") String recipeId);

}
