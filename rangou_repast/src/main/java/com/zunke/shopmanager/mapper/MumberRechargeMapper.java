package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.MumberRecharge;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员充值表(MumberRecharge)表数据库访问层
 *
 * @author hy
 * @version 1.0
 * @since 2021-09-17 08:50:23
 */

public interface MumberRechargeMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<MumberRecharge> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MumberRecharge selectById(@Param("id") Integer id);

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
    List<MumberRecharge> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param mumberRecharge 实例对象
     */
    void insert(MumberRecharge mumberRecharge);

    /**
     * 修改数据
     *
     * @param mumberRecharge 实例对象
     * @return 影响行数
     */
    int updateById(MumberRecharge mumberRecharge);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

}
