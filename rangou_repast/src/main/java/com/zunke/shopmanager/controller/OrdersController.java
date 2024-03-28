package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.pojo.Orders;
import com.zunke.shopmanager.service.impl.OrdersServiceImpl;
import com.zunke.shopmanager.service.impl.ReportsOrdersServiceImpl;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 订单表(Orders)表控制层
 *
 * @author makejava
 * @since 2021-09-14 16:38:24
 * @version 1.0
 */
@RestController
@RequestMapping("/orders")
@Api(value = "订单表(Orders)管理",tags = "订单表(Orders)管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersServiceImpl ordersServiceImpl;

    @Resource
    private ReportsOrdersServiceImpl reportsOrdersServiceImpl;

    /**
     * @Author Yiming
     * @Description 订单报表、流水表（含模糊查询、分页）
     * @Date 13:38 2021/9/21
     * @Param [index, totalPrice, startTime, endTime]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping(value = "/selectForPage", method = RequestMethod.GET)
    @ApiOperation(value = "流水查询分页接口",notes = "流水查询分页接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "name",value = "**名称",paramType = "query",dataType = "String",required = false)
    })

    public Map<String, Object> selectForPage(@RequestParam(name = "page") int index, String totalPrice ,String startTime,String endTime) {
        System.out.println(startTime);
        return this.reportsOrdersServiceImpl.selectForPage(index, totalPrice, startTime, endTime);
    }
    
    /**
     * 查询所有数据
     * @return 返回所有数据
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有数据接口",notes = "查询所有数据接口",httpMethod = "GET")
    public Map<String, Object> selectAll() {
        return this.ordersServiceImpl.selectAll();
    }
    
    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "根据一个主键删除一条**记录的接口",notes = "根据一个主键删除一条**记录的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键",paramType = "query",dataType = "String",required = true),
    })
    public Map<String, Object> deleteById(@RequestParam("id") String id) {
        return this.ordersServiceImpl.deleteById(id);
    }
    
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过主键查询一个**的接口",notes = "通过主键查询一个**的接口",httpMethod = "GET")
    public Map<String, Object> selectById(Integer id) {
        return this.ordersServiceImpl.selectById(id);
    }
    
    /**
     * 通过主键更新单条数据
     *
     * @param orders 一个数据库对应的POJO数据对象
     * @return 单条数据
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ApiOperation(value = "通过主键更新一个**的接口",notes = "通过主键更新一个**的接口",httpMethod = "POST")
    public Map<String, Object> updateById(@RequestBody @ApiParam(name = "orders",value = "pojo模型",required = true) Orders orders) {
        return this.ordersServiceImpl.updateById(orders);
    }
    
    /**
     * 通过一个pojo对象新增单条数据
     *
     * @param orders 一个数据库对应的POJO数据对象
     * @return 返回插入的主键id
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "根据完整记录新增一个**的接口",notes = "根据完整记录新增一个**的接口",httpMethod = "POST")
    public Map<String, Object> insert(@RequestBody @ApiParam(name = "orders",value = "pojo模型",required = true) Orders orders) {
        return this.ordersServiceImpl.insert(orders);
    }

    /**
     * 通过一个购物车id查询生成一个订单
     *
     * @param CartId 一个数据库对应的POJO数据对象
     * @return 返回插入的订单号码
     */
    @RequestMapping(value = "/selectByCartId", method = RequestMethod.GET)
    @ApiOperation(value = "根据购物车id查询并生成订单的接口",notes = "根据购物车id查询并生成订单的接口",httpMethod = "GET")
    public Map<String, Object> selectByCartId(@RequestParam("CartId")Integer CartId,Double totalPrice) {

        return this.ordersServiceImpl.selectByCartId(CartId,totalPrice);
    }

    @RequestMapping(value = "/selectByOrdersId", method = RequestMethod.GET)
    @ApiOperation(value = "根据订单id查询订单详情的接口",notes = "根据订单id查询订单详情的接口",httpMethod = "GET")
    public Map<String, Object> selectByOrdersId(@RequestParam("OrdersID")Integer OrdersID) {

        return this.ordersServiceImpl.selectByOrdersId(OrdersID);
    }

    @RequestMapping(value = "/updateByOrdersNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据OrdersNo生成支付时间",notes = "根据OrdersNo生成支付时间的1接口",httpMethod = "POST")
    public Map<String,Object> updateByOrdersNo(@RequestParam("OrdersNo")String OrdersNo){

        return this.ordersServiceImpl.updateByOrdersNo(OrdersNo);
    }

}