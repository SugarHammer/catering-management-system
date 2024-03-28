package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.CartDishesComboMapper;
import com.zunke.shopmanager.pojo.CartDishesCombo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车菜品套菜中间表(CartDishesCombo)表服务实现类
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-22 14:39:19
 */

@Service("cartDishesComboService")
public class CartDishesComboServiceImpl {
    @Resource
    private CartDishesComboMapper cartDishesComboMapper;

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
        map.put("data", this.cartDishesComboMapper.selectForCount(name));
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.cartDishesComboMapper.selectAll());
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
        map.put("data", this.cartDishesComboMapper.selectById(id));
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
        int tableCount = this.cartDishesComboMapper.selectForCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / limit + 1;
        // 计算每页开始的下标值
        index = (index - 1) * limit;
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 0);
        map.put("msg", "查询成功");
        // 查询的记录总页码
        map.put("pageCount", pageCount);
        // 当前表中的总条数
        map.put("count", tableCount);
        map.put("data", this.cartDishesComboMapper.selectForPage(index, limit, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param cartDishesCombo 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(CartDishesCombo cartDishesCombo) {

        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        this.cartDishesComboMapper.insert(cartDishesCombo);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param cartDishesCombo 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(CartDishesCombo cartDishesCombo) {
        this.cartDishesComboMapper.updateById(cartDishesCombo);
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
        this.cartDishesComboMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 新增数据
     *
     * @param cartDishesCombo 实例对象
     * @return 是否成功
     */
    public Map<String, Object> add(CartDishesCombo cartDishesCombo) {
        CartDishesCombo cartDishesCombo_res = cartDishesComboMapper.selectByCartIdAndDishesIdOrComboId(cartDishesCombo);
        System.out.println(cartDishesCombo_res);
        Map<String, Object> map = new HashMap<>();
        if (cartDishesCombo_res == null){
            cartDishesComboMapper.insert(cartDishesCombo);
            map.put("code", 200);
            map.put("msg", "新增成功");
            return map;
        }
        System.out.println("add--------cartDishesCombo_res != null");
        System.out.println(cartDishesCombo_res);
        this.cartDishesComboMapper.addNumber(cartDishesCombo_res);
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "修改成功");
        return map;
    }

    /**
     * 减少数据数据
     *
     * @param cartDishesCombo 实例对象
     * @return 是否成功
     */
    public Map<String, Object> subtract(CartDishesCombo cartDishesCombo) {
        CartDishesCombo cartDishesCombo_res = cartDishesComboMapper.selectByCartIdAndDishesIdOrComboId(cartDishesCombo);
        System.out.println(cartDishesCombo_res);
        Map<String, Object> map = new HashMap<>();
        if ((cartDishesCombo_res.getDishesNumber() != null && cartDishesCombo_res.getDishesNumber() == 1 )|| (cartDishesCombo_res.getComboNumber() != null && cartDishesCombo_res.getComboNumber() == 1)){
            cartDishesComboMapper.deleteByNumber(cartDishesCombo);
            map.put("code", 200);
            map.put("msg", "删除成功");
            return map;
        }
        System.out.println(cartDishesCombo_res);
        this.cartDishesComboMapper.cutNumber(cartDishesCombo_res);
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "修改成功");
        return map;
    }

    /**
     * 清空购物车
     *
     * @param cartId 购物车主键
     * @return 是否成功
     */
    public Map<String, Object> clearCart(Integer cartId) {
        Map<String, Object> map = new HashMap<>();
        this.cartDishesComboMapper.clearCart(cartId);
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "清空成功");
        return map;
    }
}
