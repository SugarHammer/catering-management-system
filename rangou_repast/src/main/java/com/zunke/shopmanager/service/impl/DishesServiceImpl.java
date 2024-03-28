package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.DishesDTO;
import com.zunke.shopmanager.dto.SelectByCartId;
import com.zunke.shopmanager.mapper.CartMapper;
import com.zunke.shopmanager.mapper.DishesMapper;
import com.zunke.shopmanager.pojo.Dishes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品(Dishes)表服务实现类
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:33:56
 */

@Service("dishesService")
public class DishesServiceImpl {
    @Resource
    private DishesMapper dishesMapper;
    @Resource
    private CartMapper cartMapper;

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
//        map.put("data", this.dishesMapper.selectForCount(name));
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    public Map<String, Object> selectAll(int index, int limit, String name, String dishesAction) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        int tableCount = this.dishesMapper.selectForCount(name, dishesAction);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / limit + 1;
        // 计算每页开始的下标值
        index = (index - 1) * limit;
        map.put("code", 200);
        map.put("msg", "查询成功");
        // 查询的记录总页码
        map.put("pageCount", pageCount);
        // 当前表中的总条数
        map.put("count", tableCount);
        map.put("data", this.dishesMapper.selectAll(index, limit, name, dishesAction));
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
        map.put("data", this.dishesMapper.selectById(id));
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
//        int tableCount = this.dishesMapper.selectForCount(name);
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
        map.put("data", this.dishesMapper.selectForPage(index, limit, name));
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
        this.dishesMapper.insert(dishes);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }
//    /**
//     * 新增数据包括图片
//     *
//     * @param dishes 实例对象
//     * @return 实例对象
//     */
//    public Map<String, Object> insert(Dishes dishes, MultipartFile file, HttpServletRequest request) throws IOException {
//        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
//
//        Map<String, Object> map = new HashMap<>();
//        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
//
//
//        String abPath = "upload/photo";
//        String uploadPath = request.getServletContext().getRealPath("") + File.separator + abPath + File.separator;
//        String fileName = file.getOriginalFilename();
//        File newFile = new File(uploadPath + fileName);
//        FileCopyUtils.copy(file.getBytes(), newFile);
//        String userSQLPath = File.separator + abPath + File.separator + fileName;
//        dishes.setDishesPhoto(userSQLPath);
//        this.dishesMapper.insert(dishes);
//
//        map.put("code", 200);
//        map.put("msg", "新增成功");
//        return map;
//    }

    /**
     * 通过ID查询单条数据
     *
     * @param dishes 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Dishes dishes) {
        this.dishesMapper.updateById(dishes);
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
        this.dishesMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }

//    /**
//     * 根据菜品小类id查询数据
//     *
//     * @return 返回所有数据
//     */
//    public Map<String, Object> selectByCategoriesId(String id) {
//        Map<String, Object> map = new HashMap<>();
//        List<Dishes> dishes = dishesMapper.selectByCategoriesId(id);
//        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
//        map.put("code", 200);
//        map.put("msg", "查询成功");
//        map.put("data", );
//        return map;
//    }

    /**
     * 通过套菜查询属于套菜的菜品
     *
     * @param id 套菜主键
     * @return 是否成功
     */
    public Map<String, Object> selectByCombo(String id) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.dishesMapper.selectByCombo(id));
        return map;
    }

    /**
     * 通过套菜查询不属于套菜的菜品
     *
     * @param id 套菜主键
     * @return 是否成功
     */
    public Map<String, Object> selectByNotCombo(String id) {
        List<DishesDTO> dishesDTOS = this.dishesMapper.selectByCombo(id);
        List<Integer> ids = new ArrayList<>();
        for (DishesDTO dishes:dishesDTOS) {
            ids.add(dishes.getId());
        }
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.dishesMapper.selectByNotCombo(ids));
        return map;
    }

    /**
     * 通过套菜查询不属于套菜的菜品
     *
     * @param categoriesId 大类主键
     * @return 是否成功
     */
    public Map<String, Object> selectByCategories(String categoriesId, Integer cartId) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        List<SelectByCartId> selectByCartIds = cartMapper.selectByCartId(cartId);
        List<DishesDTO> dishesDTOS = this.dishesMapper.selectByCategories(categoriesId);
        for (DishesDTO dishes : dishesDTOS){
            for(SelectByCartId dishesCount: selectByCartIds){
                if(dishes.getId().equals(dishesCount.getId())){
                    dishes.setDishesNumber(dishesCount.getDishesNumber());
                }
            }
        }
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", dishesDTOS);
        return map;
    }
}
