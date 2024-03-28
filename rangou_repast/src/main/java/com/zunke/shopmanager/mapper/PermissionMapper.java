package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author ZhangHongNeng
 * @since 2021-09-16 17:11:41
 * @version 1.0
 */
public interface PermissionMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<Permission> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission selectById(@Param("id") Integer id);

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
    List<Permission> selectForPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     */
    void insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int updateById(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

}