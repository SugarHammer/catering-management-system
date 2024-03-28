package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Dining;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 餐桌表(Dining)表数据库访问层
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:20:02
 * @version 1.0
 */
public interface DiningMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<Dining> selectAll();

    /**
     * 通过ID查询数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dining selectById(@Param("id") Integer id);

    /**
     * 根据条件查询
     * @param diningType    餐桌类型
     * @param diningNumber  餐桌可以坐的人数
     * @param diningAreaId  餐桌区域的主键
     * @param diningAction  餐桌状态
     * @return              返回总条数
     */
    int selectForCount(@Param("diningType")String diningType,@Param("diningNumber") String diningNumber,
                       @Param("diningAreaId")Integer diningAreaId, @Param("diningAction")Integer diningAction);

    /**
     * 根据条件查询
     * @param index         需要查询的页码
     * @param diningType    餐桌类型
     * @param diningNumber  餐桌可以坐的人数
     * @param diningAreaId  餐桌区域的主键
     * @param diningAction  餐桌状态
     * @return              返回所有结果
     */
    List<Dining> selectForPage(@Param("index") int index,@Param("diningType")String diningType,@Param("diningNumber") String diningNumber,
                               @Param("diningAreaId")Integer diningAreaId, @Param("diningAction")Integer diningAction);

    /**
     * 新增数据
     *
     * @param dining 实例对象
     */
    void insert(Dining dining);

    /**
     * 修改数据
     *
     * @param dining 实例对象
     * @return 影响行数
     */
    int updateById(Dining dining);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);


    /**
     * 根据Excel模板批量新增餐桌
     * @param list  新增对象数组
     */
    void insertAll(List<Dining> list);

    List<Dining> selectByDiningAction(Integer diningAction);
}