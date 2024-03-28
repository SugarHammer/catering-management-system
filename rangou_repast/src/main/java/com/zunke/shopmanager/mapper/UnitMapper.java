package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Unit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单位(Unit)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:09:08
 */

public interface UnitMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Unit> selectAll(@Param("index") Integer index, @Param("limit") Integer limit, @Param("unit") Unit unit);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Unit selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @param unit 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(Unit unit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<Unit> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param unit 实例对象
     */
    void insert(Unit unit);

    /**
     * 修改数据
     *
     * @param unit 实例对象
     * @return 影响行数
     */
    int updateById(Unit unit);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过Name查询单条数据
     *
     * @param unitName 单位名称
     * @return 实例对象
     */
    Unit selectByName(@Param("unitName") String unitName);
}
