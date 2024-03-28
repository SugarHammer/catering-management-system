package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Dishes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜品表(Dishes)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-16 16:06:42
 * @version 1.0
 */
public interface ReportsDishesMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<Dishes> selectAll();

    List<String> selectDishesNames(@Param("dishesName") String dishesName);

    List<String> selectAllDishesNames();

    Double selectDishesPriceByDishesName(@Param("dishesName") String dishesName);

    int selectIdByDishesName(@Param("dishesName") String dishesName);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dishes selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("name") String name);

    /**
     * 通过实体作为筛选条件查询
     * 
     * @param index 查询起始位置
     * @param name  查询条件
     * @return 对象列表
     */
    List<Dishes> selectForPage(@Param("index") int index, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param dishes 实例对象
     */
    void insert(Dishes dishes);

    /**
     * 修改数据
     *
     * @param dishes 实例对象
     * @return 影响行数
     */
    int updateById(Dishes dishes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

}