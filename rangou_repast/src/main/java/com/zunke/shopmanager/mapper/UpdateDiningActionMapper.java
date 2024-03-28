package com.zunke.shopmanager.mapper;

public interface UpdateDiningActionMapper {
    // 通过购物车ID查找餐桌ID
    Integer SelectDiningIdByCartId(String cartId);
    // 通过餐桌ID修改餐桌状态
    void UpdateDiningActionByDiningId(Integer diningId);
}
