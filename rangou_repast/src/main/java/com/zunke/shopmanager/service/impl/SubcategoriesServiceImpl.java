package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.SubcategoriesDTO;
import com.zunke.shopmanager.mapper.CategoriesMapper;
import com.zunke.shopmanager.mapper.DishesMapper;
import com.zunke.shopmanager.mapper.SubcategoriesMapper;
import com.zunke.shopmanager.pojo.Categories;
import com.zunke.shopmanager.pojo.Dishes;
import com.zunke.shopmanager.pojo.Subcategories;
import com.zunke.shopmanager.vo.SubcategoriesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品小类(Subcategories)表服务实现类
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-13 14:00:15
 */

@Service("subcategoriesService")
public class SubcategoriesServiceImpl {
    @Resource
    private SubcategoriesMapper subcategoriesMapper;
    @Resource
    private CategoriesMapper categoriesMapper;
    @Resource
    private DishesMapper dishesMapper;

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
//        map.put("data", this.subcategoriesMapper.selectForCount(name));
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    public Map<String, Object> selectAll(int index, int limit, SubcategoriesVO subcategoriesVO) {
        Map<String, Object> map = new HashMap<>();
        int tableCount = this.subcategoriesMapper.selectForCount(subcategoriesVO);
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        int pageCount = (tableCount - 1) / limit + 1;
        // 计算每页开始的下标值
        index = (index - 1) * limit;
//        subcategoriesVO.setIndex(index);
//        subcategoriesVO.setLimit(limit);
        List<SubcategoriesDTO> dtoList = this.subcategoriesMapper.selectAll(index, limit, subcategoriesVO);
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);
        // 当前表中的总条数
        map.put("count", tableCount);
        map.put("data", dtoList);
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
        map.put("data", this.subcategoriesMapper.selectById(id));
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
//        int tableCount = this.subcategoriesMapper.selectForCount(name);
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
        map.put("data", this.subcategoriesMapper.selectForPage(index, limit, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param subcategoriesVO 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(SubcategoriesVO subcategoriesVO) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        Categories categories = categoriesMapper.selectByName(subcategoriesVO.getCategoriesName());
        Subcategories subcategories = new Subcategories();
        BeanUtils.copyProperties(subcategoriesVO, subcategories);
        subcategories.setCategoriesId(categories.getId());
        this.subcategoriesMapper.insert(subcategories);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param subcategoriesVO 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(SubcategoriesVO subcategoriesVO) {
        Map<String, Object> map = new HashMap<>();
        Categories categories = categoriesMapper.selectByName(subcategoriesVO.getCategoriesName());
        if(categories == null){
            map.put("code", 200);
            map.put("msg", "更新失败," + subcategoriesVO.getCategoriesName() + "大类不存在");
            return map;
        }
        Subcategories subcategories = new Subcategories();
        BeanUtils.copyProperties(subcategoriesVO, subcategories);
        subcategories.setCategoriesId(categories.getId());
        this.subcategoriesMapper.updateById(subcategories);
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
        List<Dishes> dishesList = dishesMapper.selectBySubcategoriesId(id);
        Map<String, Object> map = new HashMap<>();
        if(dishesList.size() > 0){
            map.put("code", 500);
            map.put("msg", "删除失败,该小类有菜品数据！");
            return map;
        }
        this.subcategoriesMapper.deleteById(id);
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }
    /**
     * 通过ID查询菜品数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Map<String, Object> selectDishesBySubcategoriesId(String id) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.subcategoriesMapper.selectDishesBySubcategoriesId(id));
        return map;
    }

    /**
     * 查询菜品小类的所有id和名称
     *
     * @return 实例对象
     */
    public Map<String, Object> selectIdAndName() {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.subcategoriesMapper.selectIdAndName());
        return map;
    }


}
