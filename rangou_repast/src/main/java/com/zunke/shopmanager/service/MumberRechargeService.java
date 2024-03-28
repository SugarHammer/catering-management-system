package com.zunke.shopmanager.service;

import com.zunke.shopmanager.pojo.MumberRecharge;

import java.util.Map;

/**
 * 会员充值表(MumberRecharge)表服务接口类
 *
 * @author hy
 * @version 1.0
 * @since 2021-09-17 08:50:21
 */

public interface MumberRechargeService {
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
     * @param mumberRecharge 实例对象
     * @return 实例对象
     */
    Map<String, Object> insert(MumberRecharge mumberRecharge);

    /**
     * 通过ID查询单条数据
     *
     * @param mumberRecharge 实例对象
     * @return 实例对象
     */
    Map<String, Object> updateById(MumberRecharge mumberRecharge);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Map<String, Object> deleteById(String id);
}
