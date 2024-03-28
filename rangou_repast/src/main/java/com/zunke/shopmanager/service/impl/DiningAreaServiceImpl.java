package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.pojo.DiningArea;
import com.zunke.shopmanager.mapper.DiningAreaMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 餐桌区域表(DiningArea)表服务实现类
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:00:52
 * @version 1.0
 */
@Service("diningAreaService")
public class DiningAreaServiceImpl {
    @Resource
    private DiningAreaMapper diningAreaMapper;

//    /**
//     * 根据模糊条件查询总个数
//     *
//     * @param name 查询条件
//     * @return 返回查询到的总个数
//     */
//    public Map<String, Object> selectForCount(String name) {
//        Map<String, Object> map = new HashMap<>();
//        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
//        map.put("code", 200);
//        map.put("msg", "查询成功");
//        map.put("data", this.diningAreaMapper.selectForCount(name));
//        return map;
//    }

    /**
     * 查询所有数据
     * @param diningAreaName        餐桌区域的名字
     * @param diningAreaCoding      餐桌区域的编码
     * @return                      得到查询后的内容
     */
    public Map<String, Object> selectAll(String  diningAreaName, String diningAreaCoding) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diningAreaMapper.selectAll(diningAreaName,diningAreaCoding));
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id    餐桌区域主键
     * @return      实例对象
     */
    public Map<String, Object> selectById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diningAreaMapper.selectById(id));
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @return      对象列表
     */
    public Map<String, Object> selectForPage(int index, String name) {
        // 获取当前表中的总记录
        int tableCount = this.diningAreaMapper.selectForCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / 10 + 1;
        // 计算每页开始的下标值
        index = (index - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        // 查询的记录总页码
        map.put("pageCount", pageCount);
        // 当前表中的总条数
        map.put("count", tableCount);
        map.put("data", this.diningAreaMapper.selectForPage(index, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param diningArea    实例对象
     * @return              是否成功
     */
    public Map<String, Object> insert(DiningArea diningArea) {
        this.diningAreaMapper.insert(diningArea);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param diningArea 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(DiningArea diningArea) {
        this.diningAreaMapper.updateById(diningArea);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "更新成功");
        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param id    餐桌区域主键
     * @return      是否成功
     */
    public Map<String, Object> deleteById(String id) {
        this.diningAreaMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 按区域查询所有餐桌
     * @param diningAreaName    区域名称
     * @return  返回多条数据
     */
    public Map<String, Object> selectByArea(String diningAreaName) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diningAreaMapper.selectByArea(diningAreaName));
        return map;
    }

}