package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.SubcategoriesDTO;
import com.zunke.shopmanager.pojo.Dishes;
import com.zunke.shopmanager.pojo.Subcategories;
import com.zunke.shopmanager.vo.SubcategoriesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜品小类(Subcategories)表数据库访问层
 *
 * @author Hernie
 * @version 1.0
 * @since 2021-09-13 14:00:16
 */

public interface SubcategoriesMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<SubcategoriesDTO> selectAll(@Param("index") int index, @Param("limit") int limit, @Param("subcategoriesVO") SubcategoriesVO subcategoriesVO);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Subcategories selectById(@Param("id") Integer id);

    /**
     * 根据模糊条件查询总个数
     *
     * @param subcategoriesVO 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(SubcategoriesVO subcategoriesVO);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<Subcategories> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param subcategories 实例对象
     */
    void insert(Subcategories subcategories);

    /**
     * 修改数据
     *
     * @param subcategories 实例对象
     * @return 影响行数
     */
    int updateById(Subcategories subcategories);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过ID查询菜品数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<Dishes> selectDishesBySubcategoriesId(@Param("id") String id);

    /**
     * 查询菜品小类的所有id和名称
     *
     * @return 实例对象
     */
    List<Dishes> selectIdAndName();

    /**
     * 查询菜品小类的所有id和名称
     *
     * @return 实例对象
     */
    Subcategories selectByNameAndCategoriesId(@Param("subcategoriesName") String subcategoriesName, @Param("categoriesId") Integer categoriesId);

    /**
     * 通过主键跟新图片数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int updateComboPhoto(@Param("photo") String photo, @Param("id") String id);
}
