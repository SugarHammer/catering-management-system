package com.zunke.shopmanager.mapper;

public interface CopyInfoMapper {
    /**
     * @Author Yiming
     * @Description //复制购物车中套菜信息到信息表
     * @Date 12:55 2021/9/26
     * @Param [cartId]
     * @return void
     **/
    void copyCombosInfo(String cartId);
    /**
     * @Author Yiming
     * @Description //复制购物车中菜品信息到信息表
     * @Date 12:55 2021/9/26
     * @Param [cartId]
     * @return void
     **/
    void copyDishesInfo(String cartId);
}
