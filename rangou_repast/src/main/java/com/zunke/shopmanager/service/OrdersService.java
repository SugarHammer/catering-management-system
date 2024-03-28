package com.zunke.shopmanager.service;

import com.zunke.shopmanager.pojo.Orders;

import java.util.Map;

/**
 * 订单表(Orders)表服务接口类
 *
 * @author yuzhe
 * @since 2021-09-16 10:15:06
 * @version 1.0
 */
public interface OrdersService {
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
     * @return  返回所有数据
     */
    Map<String, Object> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param   id 主键
     * @return 实例对象
     */
    Map<String, Object> selectById(Integer id);

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name 查询条件
     * @return 对象列表
     */
    Map<String, Object> selectForPage(int index, String name);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(Orders orders);

    /**
     * 通过ID查询单条数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    Map<String, Object> updateById(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param  id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(Integer id);
}