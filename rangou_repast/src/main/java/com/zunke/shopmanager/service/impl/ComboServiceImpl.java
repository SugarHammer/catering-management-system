package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.CartCombo;
import com.zunke.shopmanager.dto.ComboCount;
import com.zunke.shopmanager.mapper.ComboMapper;
import com.zunke.shopmanager.mapper.DishesComboMapper;
import com.zunke.shopmanager.mapper.UnitMapper;
import com.zunke.shopmanager.pojo.Combo;
import com.zunke.shopmanager.pojo.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 套菜(Combo)表服务实现类
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:16:28
 */

@Service("comboService")
public class ComboServiceImpl {
    @Resource
    private ComboMapper comboMapper;
    @Resource
    private DishesComboMapper dishesComboMapper;

    @Resource
    private UnitMapper unitMapper;

    /**
     * 根据模糊条件查询总个数
     *
     * @param comboNo 查询条件
     * @return 返回查询到的总个数
     */
    public Map<String, Object> selectForCount(String comboNo, String comboName) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.comboMapper.selectForCount(comboNo, comboName));
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    public Map<String, Object> selectAll(int index, int limit, String comboNo, String comboName) {
        Map<String, Object> map = new HashMap<>();
        int tableCount = this.comboMapper.selectForCount(comboNo, comboName);
        int pageCount = (tableCount - 1) / limit + 1;
        index = (index - 1) * limit;
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        // 查询的记录总页码
        map.put("pageCount", pageCount);
        // 当前表中的总条数
        map.put("count", tableCount);
        map.put("data", this.comboMapper.selectAll(index, limit, comboNo, comboName));
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
        map.put("data", this.comboMapper.selectById(id));
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    public Map<String, Object> selectForPage(int index, int limit, String name) {
        // 获取当前表中的总记录
//        int tableCount = this.comboMapper.selectForCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
//        int pageCount = (tableCount - 1) / limit + 1;
        // 计算每页开始的下标值
        index = (index - 1) * limit;
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 0);
        map.put("msg", "查询成功");
        // 查询的记录总页码
//        map.put("pageCount", pageCount);
        // 当前表中的总条数
//        map.put("count", tableCount);
        map.put("data", this.comboMapper.selectForPage(index, limit, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param combo 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Combo combo) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        Unit unit = new Unit();
        unit.setUnitName("份");
        int count = unitMapper.selectForCount(unit);
        List<Unit> unitList = unitMapper.selectAll(0, count,unit);
        Integer id;
        if(count > 0) {
            id = unitList.get(0).getId();
        }else {
            unitMapper.insert(unit);
            unitList = unitMapper.selectAll(0, count, unit);
            id = unitList.get(0).getId();
        }
        combo.setUnitId(id);
        this.comboMapper.insert(combo);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param combo 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Combo combo) {
        double count = this.comboMapper.selectForPriceCount(combo.getId());
//        combo.setComboPrice(count);
        this.comboMapper.updateById(combo);
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
    @Transactional
    public Map<String, Object> deleteById(String id) {
        this.comboMapper.deleteById(id);
        this.dishesComboMapper.deleteByDishesIdAndComboId(id, null);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    public Map<String, Object> searchAll(Integer cartId) {
        Map<String, Object> map = new HashMap<>();
        List<CartCombo> cartCombos = this.comboMapper.searchAll();
        List<ComboCount> comboCounts = comboMapper.searchComboCount(cartId);
        for (CartCombo cartCombo : cartCombos){
            for (ComboCount comboCount : comboCounts){
                    if (cartCombo.getId() != null && cartCombo.getId().equals(comboCount.getId())) {
                        cartCombo.setDishesNumber(comboCount.getComboNumber());
                }
            }
        }

        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", cartCombos);
        return map;
    }

}
