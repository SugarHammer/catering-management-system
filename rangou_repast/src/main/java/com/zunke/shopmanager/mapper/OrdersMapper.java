package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 订单表(Orders)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-14 16:38:24
 * @version 1.0
 */
public interface OrdersMapper {

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<Orders> selectAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orders selectById(@Param("id") Integer id);


    /**
     * 通过多个ID查询多条数据
     *
     * @param list 选中的主键集合
     * @return 实例对象
     */
    List<Orders> selectByIds(List<String> list);


    /**
     * 根据模糊条件查询总个数
     *
     * @return 返回查询到的总个数
     */
    int selectForCount(@Param("totalPrice") String totalPrice,@Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * @Author Yiming
     * @Description //报表中心的流水表
     * @Date 12:56 2021/9/15
     * @Param [index, totalPrice, startTime, endTime]
     * @return java.util.List<com.zunke.shopmanager.pojo.Orders>
     **/
    List<Orders> selectForPage(@Param("index") int index, @Param("totalPrice")String totalPrice,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     */
    void insert(Orders orders);

    /**
     * 多表联查生成一个订单信息
     * @return
     */
    Orders selectByCartId(int id);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int updateById(Orders orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 支付完修改订单支付时间
     * @return  返回所有数据
     */
    int updateByOrdersNo(@Param("ordersNo")String ordersNo, @Param("date")Date date);
}