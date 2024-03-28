package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.CartCombo;
import com.zunke.shopmanager.dto.ComboCount;
import com.zunke.shopmanager.pojo.Combo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套菜(Combo)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:16:28
 */

public interface ComboMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Combo> selectAll(@Param("index") Integer index, @Param("limit") Integer limit, @Param("comboNo") String comboNo, @Param("comboName") String comboName);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Combo selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @param comboNo 查询条件
     * @param comboName 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("comboNo") String comboNo, @Param("comboName") String comboName);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<Combo> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param combo 实例对象
     */
    void insert(Combo combo);

    /**
     * 修改数据
     *
     * @param combo 实例对象
     * @return 影响行数
     */
    int updateById(Combo combo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);


    /**
     * 根据主键查询价格总数
     *
     * @param id 查询条件
     * @return 返回查询到的总个数
     */
    double selectForPriceCount(@Param("id") Integer id);


    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<CartCombo> searchAll();


    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<ComboCount> searchComboCount(Integer cartId);
}
