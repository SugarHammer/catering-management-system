package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.ComboInfoDTO;
import com.zunke.shopmanager.pojo.ComboInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套餐信息表(ComboInfo)表数据库访问层
 * @author yuzhe
 * @since 2021-09-16 10:08:45
 * @version 1.0
 */
public interface ComboInfoMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<ComboInfo> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ComboInfo selectById(@Param("id") Integer id);

    List<ComboInfoDTO> selectByOrderId(@Param("orderId") Integer orderId);

    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("name") String name);

    /**
     * 通过实体作为筛选条件查询
     * 
     * @param index 查询起始位置
     * @param name  查询条件
     * @return 对象列表
     */
    List<ComboInfo> selectForPage(@Param("index") int index, @Param("name")String name);

    /**
     * 新增数据
     *
     * @param comboInfo 实例对象
     */
    void insert(ComboInfo comboInfo);

    /**
     * 修改数据
     *
     * @param comboInfo 实例对象
     * @return 影响行数
     */
    int updateById(ComboInfo comboInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

}