package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.CopyInfoMapper;
import com.zunke.shopmanager.mapper.UpdateDiningActionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/26 11:37
 */
@Service
public class AfterPayServiceImpl {
    @Resource
    private CopyInfoMapper copyInfoMapper;

    @Resource
    private UpdateDiningActionMapper updateDiningActionMapper;
    public void AfterPay(HttpServletRequest request, HttpServletResponse response,String cartId){
                // 复制购物车信息表（cart_dishes_combo）中的套菜信息和单品信息到combo表和dishes表
                copyInfoMapper.copyCombosInfo(cartId);
                copyInfoMapper.copyDishesInfo(cartId);
                // 复制购物车信息表到订单表

                // 修改餐桌action为非占用
                updateDiningActionMapper.UpdateDiningActionByDiningId(updateDiningActionMapper.SelectDiningIdByCartId(cartId));
    }
}
