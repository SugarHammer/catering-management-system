package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.ReportsDishesMapper;
import com.zunke.shopmanager.pojo.Dishes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 菜品表(Dishes)表服务实现类
 *
 * @author makejava
 * @since 2021-09-16 16:06:42
 * @version 1.0
 */
@Service("reportsdishesService")
public class ReportsDishesServiceImpl {
    @Resource
    private ReportsDishesMapper reportsDishesMapper;

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    public Map<String, Object> selectForCount(String name) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.reportsDishesMapper.selectForCount(name));
        return map;
    }

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.reportsDishesMapper.selectAll());
        return map;
    }

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectDishesNames(String dishesName) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", reportsDishesMapper.selectDishesNames(dishesName));
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
        map.put("data", this.reportsDishesMapper.selectById(id));
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @return 对象列表
     */
    public Map<String, Object> selectForPage(int index, String name) {
    // 获取当前表中的总记录
        int tableCount = this.reportsDishesMapper.selectForCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / 10 + 1;
        // 计算每页开始的下标值
        index = (index - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);  // 查询的记录总页码
        map.put("count", tableCount);     // 当前表中的总条数
        map.put("data", this.reportsDishesMapper.selectForPage(index, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param dishes 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Dishes dishes) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        this.reportsDishesMapper.insert(dishes);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param dishes 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Dishes dishes) {
        this.reportsDishesMapper.updateById(dishes);
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
        this.reportsDishesMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "删除成功");
        return map;
    }
}