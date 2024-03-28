package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Recipe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 做法(Recipe)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:16:29
 */

public interface RecipeMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Recipe> selectAll(@Param("index") Integer index, @Param("limit") Integer limit, @Param("recipeType") String recipeType);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Recipe selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @param recipeType 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("recipeType") String recipeType);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<Recipe> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param recipe 实例对象
     */
    void insert(Recipe recipe);

    /**
     * 修改数据
     *
     * @param recipe 实例对象
     * @return 影响行数
     */
    int updateById(Recipe recipe);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过菜品ID查询做法数据
     *
     * @param id 菜品主键主键
     * @return 查询到的数据
     */
    List<Recipe> selectByDishesId(@Param("id") String id);

    /**
     * 通过菜品ID查询不是改菜品做法的数据
     *
     * @param id 菜品主键主键
     * @return 查询到的数据
     */
    List<Recipe> selectByDishesIdIsNot(@Param("id") String id);

    /**
     * 通过菜品ID查询不是改菜品做法的数据
     *
     * @param list 菜品主键主键
     * @return 查询到的数据
     */
    List<Recipe> selectByDishesIds(List<Integer> list);
}
