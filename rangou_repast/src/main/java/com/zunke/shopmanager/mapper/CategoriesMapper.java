package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.SubcategoriesDTO;
import com.zunke.shopmanager.pojo.Categories;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜品大类(Categories)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-12 16:26:32
 */

public interface CategoriesMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Categories> selectAll(@Param("index") int index, @Param("limit") int limit, @Param("categoriesName") String categoriesName);


    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Categories> selectAllCategories();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Categories selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("name") String name);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<Categories> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param categories 实例对象
     */
    void insert(Categories categories);

    /**
     * 修改数据
     *
     * @param categories 实例对象
     * @return 影响行数
     */
    int updateById(Categories categories);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过主键查询数据
     *
     * @param id 主键
     * @return 影响行数
     */
    List<SubcategoriesDTO> selectSubcategoriesByCategoriesId(@Param("id") String id);
    /**
     * 通过名称查询数据
     *
     * @param categoriesName 名称
     * @return 影响行数
     */
    Categories selectByName(@Param("categoriesName") String categoriesName);

}
