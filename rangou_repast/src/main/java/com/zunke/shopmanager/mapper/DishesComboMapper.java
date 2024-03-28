package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.DishesCombo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜品套菜关联表(DishesCombo)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-15 10:24:00
 */

public interface DishesComboMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<DishesCombo> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DishesCombo selectById(@Param("id") Integer id);

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
    List<DishesCombo> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param dishesCombo 实例对象
     */
    void insert(DishesCombo dishesCombo);

    /**
     * 修改数据
     *
     * @param dishesCombo 实例对象
     * @return 影响行数
     */
    int updateById(DishesCombo dishesCombo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过套菜主键和菜品主键删除
     *
     * @param comboId 套菜主键
     * @param dishesId 菜品主键
     * @return 影响行数
     */
    int deleteByDishesIdAndComboId(@Param("dishesId") String dishesId, @Param("comboId") String comboId);


    /**
     * 通过菜品主键和套菜主键更新单条数据的菜品数量
     *
     * @param dishesCombo 实例对象
     * @return 影响行数
     */
    int updateNumberByDishesIdAndComboId(DishesCombo dishesCombo);


}
