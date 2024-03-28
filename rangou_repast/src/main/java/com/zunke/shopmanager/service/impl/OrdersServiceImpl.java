package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.Util.MyUtils;
import com.zunke.shopmanager.dto.OrdersSelectDTO;
import com.zunke.shopmanager.mapper.MumberOrdersMapper;
import com.zunke.shopmanager.pojo.Orders;
import com.zunke.shopmanager.mapper.OrdersMapper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author makejava
 * @since 2021-09-14 16:38:24
 * @version 1.0
 */
@Service("ordersService")
public class OrdersServiceImpl {
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private MumberOrdersService mumberOrdersService;

    MyUtils myUtils = new MyUtils();


    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
//    public Map<String, Object> selectForCount(String name) {
//        Map<String, Object> map = new HashMap<>();
//        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
//        map.put("code", 200);
//        map.put("msg", "查询成功");
//        map.put("data", this.ordersMapper.selectForCount(name));
//        return map;
//    }

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.ordersMapper.selectAll());
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
        map.put("data", this.ordersMapper.selectById(id));
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param totalPrice  查询条件
     * @param startTime  查询条件
     * @param endTime  查询条件
     * @return 对象列表
     */
    public Map<String, Object> selectForPage(int index, String totalPrice, String startTime,String endTime) {
    // 获取当前表中的总记录
        int tableCount = this.ordersMapper.selectForCount(totalPrice, startTime, endTime);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / 10 + 1;
        // 计算每页开始的下标值
        index = (index - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);  // 查询的记录总页码
        map.put("count", tableCount);     // 当前表中的总条数
        map.put("data", this.ordersMapper.selectForPage(index, totalPrice, startTime, endTime));
        return map;
    }



    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Orders orders) {
        // UUID.randomUUID()  返回内容：asd21321-ewrewrew213213-123213zsad-123asdasd这样的形态
        System.out.println(ordersMapper);

       ordersMapper.insert(orders);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "新增成功");
        return map;
    }

    public Map<String,Object> selectByCartId(Integer cartId,Double totalPrice){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "未查询到该购物车信息,订单生成失败!");
        Orders orders = ordersMapper.selectByCartId(cartId);
        System.out.println(orders);
        if(orders!=null) {
            //orders.setCreateDate(new Date());
            //System.out.println("orders.getMumberId()========="+orders.getMumberId());
            orders.setOrdersNo(myUtils.getOrderNum(orders.getMumberId()));
            orders.setTotalPrice(totalPrice);
            orders.setIsDel(0);
           //System.out.println(orders);
            ordersMapper.insert(orders);
            map.put("code", 200);
            map.put("msg", "订单查询成功");
            map.put("data", orders.getOrdersNo());
        }
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Orders orders) {
        this.ordersMapper.updateById(orders);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
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
        this.ordersMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);   // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("msg", "删除成功");
        return map;
    }


    public Map<String, Object> selectByOrdersId(Integer ordersId) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "该订单详情为空");
        Orders orders = this.ordersMapper.selectById(ordersId);
        if(orders!=null){
            System.out.println("orders.getCartId()======="+orders.getCartId());
            Map<String, Object> map1 = mumberOrdersService.selectBycartsId(orders.getCartId());
            return map1;
        }
        return map;
    }

    public Map<String, Object> updateByOrdersNo(String ordersNo){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "支付失败!");
        Date date = new Date();
        //System.out.println("date===="+date);
        int flag = ordersMapper.updateByOrdersNo(ordersNo,date);
        if (flag!=0){
            map.put("code", "200");
            map.put("msg","支付成功!");
        }
        return map;
    }
}