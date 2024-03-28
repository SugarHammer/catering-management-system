package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Integral;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 积分交易表(Integral)表数据库访问层
 *
 * @author hy
 * @version 1.0
 * @since 2021-09-16 16:39:37
 */

public interface IntegralMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Integral> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Integral selectById(@Param("id") Integer id);

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
    List<Integral> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param integral 实例对象
     */
    void insert(Integral integral);

    /**
     * 修改数据
     *
     * @param integral 实例对象
     * @return 影响行数
     */
    int updateById(Integral integral);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

}
