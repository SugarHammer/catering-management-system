package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.DishesCount;
import com.zunke.shopmanager.pojo.CartDishesCombo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车菜品套菜中间表(CartDishesCombo)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-22 14:39:19
 */

public interface CartDishesComboMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<CartDishesCombo> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CartDishesCombo selectById(@Param("id") Integer id);

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
    List<CartDishesCombo> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param cartDishesCombo 实例对象
     */
    void insert(CartDishesCombo cartDishesCombo);

    /**
     * 修改数据
     *
     * @param cartDishesCombo 实例对象
     * @return 影响行数
     */
    int updateById(CartDishesCombo cartDishesCombo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过主键删除数据
     *
     * @param cartDishesCombo 实例对象
     * @return 影响行数
     */
    CartDishesCombo selectByCartIdAndDishesIdOrComboId(CartDishesCombo cartDishesCombo);

    /**
     * 修改数据
     *
     * @param cartDishesCombo 实例对象
     * @return 影响行数
     */
    int addNumber(CartDishesCombo cartDishesCombo);

    /**
     * 修改数据
     *
     * @param cartDishesCombo 实例对象
     * @return 影响行数
     */
    int cutNumber(CartDishesCombo cartDishesCombo);

    /**
     * 删除数据
     *
     * @param cartDishesCombo 实例对象
     * @return 影响行数
     */
    int deleteByNumber(CartDishesCombo cartDishesCombo);

    /**
     * 删除数据
     *
     * @param cartId 购物车主键
     * @return 影响行数
     */
    int clearCart(Integer cartId);

    /**
     * 根据购物车查询数量
     *
     * @param cartId 购物车主键
     * @return 影响行数
     */
    List<DishesCount> selectDishesCount(Integer cartId);

    /**
     * 根据购物车查询数量
     *
     * @param cartId 购物车主键
     * @return 影响行数
     */
    int selectComboCount(Integer cartId);

    /**
     * 根据购物车查询数量
     *
     * @param cartId 购物车主键
     * @return 影响行数
     */
//    int selectdishesNumberByCategories(Integer cartId);
}
