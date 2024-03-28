package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.RegisterNumber;
import com.zunke.shopmanager.pojo.Mumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员表(Mumber)表数据库访问层
 *
 * @author hy
 * @version 1.0
 * @since 2021-09-15 08:44:11
 */

public interface MumberMapper {

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    List<Mumber> selectAll(Mumber mumber);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Mumber selectById(@Param("id") Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param phone 手机号码
     * @return 实例对象
     */
    Mumber selectByPhone(@Param("phone") String phone);

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("name") String name);

    /**
     * 查询当天注册的所有人数
     *
     * @param time 今天的时间
     * @return 返回查询到的总个数
     */
    int selectTodayRegisterPerson(@Param("time") String time);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    List<Mumber> selectForPage(@Param("index") int index, @Param("limit") int limit, @Param("name") String name);

    /**
     * 新增数据
     *
     * @param mumber 实例对象
     */
    void insert(Mumber mumber);

    /**
     * 修改数据
     *
     * @param mumber 实例对象
     * @return 影响行数
     */
    int updateById(Mumber mumber);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 查询每月/天注册会员人数
     * @return
     */
    List<RegisterNumber> selectRegisterNumber();

}
