package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.DishesDTO;
import com.zunke.shopmanager.pojo.Dishes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜品(Dishes)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 17:33:57
 */

public interface DishesMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<DishesDTO> selectAll(@Param("index") int index, @Param("limit") int limit, @Param("name") String name, @Param("dishesAction") String dishesAction);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dishes selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("name") String name, @Param("dishesAction") String dishesAction);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<Dishes> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param dishes 实例对象
     */
    void insert(Dishes dishes);

    /**
     * 修改数据
     *
     * @param dishes 实例对象
     * @return 影响行数
     */
    int updateById(Dishes dishes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过菜品小类查询菜品
     *
     * @param subcategoriesId 菜品小类主键
     * @return 所属菜品小类的所有菜品
     */
    List<Dishes> selectBySubcategoriesId(@Param("subcategoriesId") String subcategoriesId);

    /**
     * 通过主键跟新图片数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int updatePhoto(@Param("photo") String photo, @Param("id") String id);

    /**
     * 通过套菜查询属于套菜的菜品
     *
     * @param id 套菜主键
     * @return 套菜中的数据
     */
    List<DishesDTO> selectByCombo(@Param("id") String id);

    /**
     * 通过套菜查询不属于套菜的菜品
     *
     * @param list 套菜主键
     * @return 套菜外的数据
     */
    List<DishesDTO> selectByNotCombo(List<Integer> list);


    /**
     * 通过id查询菜品
     *
     * @param list 套菜主键
     * @return 套菜外的数据
     */
    List<DishesDTO> selectByIds(List list);

    /**
     * 通过大类名称查询菜品
     *
     * @param categoriesId 大类主键
     * @return 套菜外的数据
     */
    List<DishesDTO> selectByCategories(String categoriesId);

    /**
     * 新增所有数据
     *
     * @param list 所有数据
     * @return 套菜外的数据
     */
    void insertAll(List<Dishes> list);
}
