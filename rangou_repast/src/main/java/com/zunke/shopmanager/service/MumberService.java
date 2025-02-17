package com.zunke.shopmanager.service;

import com.zunke.shopmanager.pojo.Mumber;

import java.util.Map;

/**
 * 会员表(Mumber)表服务接口类
 *
 * @author hy
 * @version 1.0
 * @since 2021-09-15 08:44:10
 */

public interface MumberService {
    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    Map<String, Object> selectForCount(String name);

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    Map<String, Object> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Map<String, Object> selectById(Integer id);

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    Map<String, Object> selectForPage(int index, int limit, String name);

    /**
     * 新增数据
     *
     * @param mumber 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(Mumber mumber);

    /**
     * 通过ID查询单条数据
     *
     * @param mumber 实例对象
     * @return 实例对象
     */
    Map<String, Object> updateById(Mumber mumber);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);
}
