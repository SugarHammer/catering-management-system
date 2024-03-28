package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.RecipeMapper;
import com.zunke.shopmanager.pojo.Recipe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 做法(Recipe)表服务实现类
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:16:29
 */

@Service("recipeService")
public class RecipeServiceImpl {
    @Resource
    private RecipeMapper recipeMapper;

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
        map.put("data", this.recipeMapper.selectForCount(name));
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    public Map<String, Object> selectAll(Integer index, Integer limit, String recipeType) {
        Map<String, Object> map = new HashMap<>();
        int tableCount = this.recipeMapper.selectForCount(recipeType);
        int pageCount = (tableCount - 1) / limit + 1;
        index = (index - 1) * limit;
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        // 查询的记录总页码
        map.put("pageCount", pageCount);
        // 当前表中的总条数
        map.put("count", tableCount);
        map.put("data", this.recipeMapper.selectAll(index, limit, recipeType));
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
        map.put("data", this.recipeMapper.selectById(id));
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
        int tableCount = this.recipeMapper.selectForCount(name);
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
        map.put("data", this.recipeMapper.selectForPage(index, limit, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param recipe 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Recipe recipe) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        this.recipeMapper.insert(recipe);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param recipe 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Recipe recipe) {
        this.recipeMapper.updateById(recipe);
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
        this.recipeMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 通过菜品ID查询做法数据
     *
     * @param id 菜品主键
     * @return 实例对象
     */
    public Map<String, Object> selectByDishesId(String id) {
        List<Recipe> recipeList = this.recipeMapper.selectByDishesId(id);
        List<Integer> list = new ArrayList<>();
        for (Recipe recipe:recipeList) {
            list.add(recipe.getId());
        }
        System.out.println(list);
        List<Recipe> recipeListNot = this.recipeMapper.selectByDishesIds(list);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "更新成功");
        map.put("data", recipeList);
        map.put("dataNot", recipeListNot);

        return map;
    }

    /**
     * 通过菜品ID查询做法数据
     *
     * @param id 菜品主键
     * @return 实例对象
     */
    public Map<String, Object> selectByDishesIdIsNot(String id) {
        List<Recipe> recipeList = this.recipeMapper.selectByDishesId(id);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "更新成功");
        map.put("data", recipeList);

        return map;
    }
}
