package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.pojo.Dining;
import com.zunke.shopmanager.mapper.DiningMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 餐桌表(Dining)表服务实现类
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:20:01
 * @version 1.0
 */
@Service("diningService")
public class DiningServiceImpl {
    @Resource
    private DiningMapper diningMapper;


    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diningMapper.selectAll());
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
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diningMapper.selectById(id));
        return map;
    }


    /**
     *
     * @param index         需要查询的页码
     * @param diningType    餐桌类型
     * @param diningNumber  餐桌可以坐的人数
     * @param diningAreaId  餐桌区域的主键
     * @param diningAction  餐桌状态
     * @return              返回所有结果
     */
    public Map<String, Object> selectForPage(int index,String diningType, String diningNumber, Integer diningAreaId, Integer diningAction) {
    // 获取当前表中的总记录
        int tableCount = this.diningMapper.selectForCount(diningType, diningNumber, diningAreaId, diningAction);
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
        map.put("data", this.diningMapper.selectForPage(index,diningType, diningNumber, diningAreaId, diningAction));
        return map;
    }

    /**
     * 新增数据
     *
     * @param dining 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Dining dining) {
        this.diningMapper.insert(dining);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param dining 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Dining dining) {
        this.diningMapper.updateById(dining);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
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
        this.diningMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }


    public Map<String, Object> selectByDiningAction(Integer diningAction) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.diningMapper.selectByDiningAction(diningAction));
        return map;
    }
}