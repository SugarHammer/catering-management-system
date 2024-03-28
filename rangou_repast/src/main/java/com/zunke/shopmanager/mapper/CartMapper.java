package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.CartDTO;
import com.zunke.shopmanager.dto.SelectByCartId;
import com.zunke.shopmanager.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车(Cart)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-22 18:59:37
 */

public interface CartMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Cart> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CartDTO selectById(@Param("id") Integer id);

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
    List<Cart> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param cart 实例对象
     */
    void insert(Cart cart);

    /**
     * 修改数据
     *
     * @param cart 实例对象
     * @return 影响行数
     */
    int updateById(Cart cart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过会员主键和购物车状态查询
     *
     * @param diningId 餐桌主键
     * @return 对象列表
     */
    CartDTO selectByDiningIdAndAction(@Param("diningId") Integer diningId);


    /**
     * 通过会员主键和购物车状态查询
     *
     * @param cartId 购物车主键
     * @return 对象列表
     */
    List<SelectByCartId> selectByCartId(@Param("cartId") Integer cartId);

}
