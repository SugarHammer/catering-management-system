package com.zunke.shopmanager.service.impl;/**
 * @author ：EdgeYu
 * @date 2021-09-23 14:21
 * @version ：1.0
 */

import com.zunke.shopmanager.dto.OrdersSelectDTO;
import com.zunke.shopmanager.mapper.MumberOrdersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MumberOrdersServerce
 * @Date 2021/9/23 14:21
 * @Author 何永波
 * @Description TODO
 */
@Service
public class MumberOrdersService {
    @Resource
    private MumberOrdersMapper mumberOrdersMapper;

    public Map<String,Object> selectBycartsId(int cartId){//通过购物车id查找用户订单
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg", "订单为空");
        List<OrdersSelectDTO> ordersSelectDTOs = mumberOrdersMapper.selectOrders(cartId);
        ArrayList arrayList = new ArrayList();
        if(ordersSelectDTOs!=null){
            map.put("msg", "订单信息查询成功!");
            for(OrdersSelectDTO ordersSelectDTO:ordersSelectDTOs){
                Map<String, Object> data = new HashMap<>();
                data.put("dishesId",ordersSelectDTO.getDishesId()!=null?ordersSelectDTO.getDishesId():ordersSelectDTO.getComboId());
                //System.out.println("ordersSelectDTO.getComboId()"+ordersSelectDTO.getComboId());
                data.put("dishesName",ordersSelectDTO.getDishesName()!=null?ordersSelectDTO.getDishesName():ordersSelectDTO.getComboName());
                data.put("dishesNumber",ordersSelectDTO.getDishesNumber()!=null?ordersSelectDTO.getDishesNumber():ordersSelectDTO.getComboNumber());
                data.put("dishesPhoto",ordersSelectDTO.getDishesPhoto()!=null?ordersSelectDTO.getDishesPhoto():ordersSelectDTO.getComboPhoto());
                data.put("dishesCount",ordersSelectDTO.getDishesCount()!=null?ordersSelectDTO.getDishesCount():ordersSelectDTO.getComboCount());

                arrayList.add(data);
            }
            map.put("data",arrayList);
        }
        return map;
    }

}
