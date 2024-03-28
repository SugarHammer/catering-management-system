package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.ReportsOrdersMapper;
import com.zunke.shopmanager.pojo.Orders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author makejava
 * @since 2021-09-14 16:38:24
 * @version 1.0
 */
@Service("reportsOrdersService")
public class ReportsOrdersServiceImpl {
    @Resource
    private ReportsOrdersMapper reportsOrdersMapper;

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
//    public Map<String, Object> selectForCount(String name) {
//        Map<String, Object> map = new HashMap<>();
//        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
//        map.put("code", 200);
//        map.put("msg", "查询成功");
//        map.put("data", this.ordersMapper.selectForCount(name));
//        return map;
//    }

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.reportsOrdersMapper.selectAll());
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Map<String, Object> selectById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.reportsOrdersMapper.selectById(id));
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param totalPrice  查询条件
     * @param startTime  查询条件
     * @param endTime  查询条件
     * @return 对象列表
     */
    public Map<String, Object> selectForPage(int index, String totalPrice, String startTime, String endTime) {
    // 获取当前表中的总记录
        int tableCount = this.reportsOrdersMapper.selectForCount(totalPrice, startTime, endTime);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / 10 + 1;
        // 计算每页开始的下标值
        index = (index - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);  // 查询的记录总页码
        map.put("count", tableCount);     // 当前表中的总条数
        map.put("data", this.reportsOrdersMapper.selectForPage(index, totalPrice, startTime, endTime));
        return map;
    }

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Orders orders) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        this.reportsOrdersMapper.insert(orders);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Orders orders) {
        this.reportsOrdersMapper.updateById(orders);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "更新成功");
        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String id) {
        this.reportsOrdersMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "删除成功");
        return map;
    }

}