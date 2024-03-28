package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.OrdersSelectDTO;
import com.zunke.shopmanager.pojo.Orders;

import java.util.List;

/**
 * @author ：何永波
 * @version ：1.0
 * @date 2021-09-23 9:31
 */
public interface MumberOrdersMapper {

   /**
    * 新增用户订单
    * @param mumberOrder
    */
   void insert(Orders mumberOrder);

   /**
    * 删除用户订单
    * @param ordersNo
    * @return
    */
   int deleteByOrdersNo(String ordersNo);

   List<OrdersSelectDTO> selectOrders(int cartId);
}
