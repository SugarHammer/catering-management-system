package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.DishesInfoDTO;
import com.zunke.shopmanager.pojo.DishesInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜品信息表(DishesInfo)表数据库访问层
 *
 * @author yuzhe
 * @since 2021-09-16 10:08:27
 * @version 1.0
 */
public interface DishesInfoMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<DishesInfo> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DishesInfo selectById(@Param("id") Integer id);


    /**
     * 通过订单id查询菜品信息数据
     *
     * @param orderid 主键
     * @return 实例对象
     */
    List<DishesInfoDTO>selectByOrderId(@Param("orderid") Integer orderid);

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
    List<DishesInfo> selectForPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param dishesInfo 实例对象
     */
    void insert(DishesInfo dishesInfo);

    /**
     * 修改数据
     *
     * @param dishesInfo 实例对象
     * @return 影响行数
     */
    int updateById(DishesInfo dishesInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

}