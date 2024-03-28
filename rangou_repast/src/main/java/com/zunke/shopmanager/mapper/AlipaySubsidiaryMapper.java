package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.CartDTO;
import org.apache.ibatis.annotations.Param;

public interface AlipaySubsidiaryMapper {
    /**
     * 通过ID查询单条数据的消费额
     *
     * @param cartId 主键
     * @return 实例对象
     */
    Double selectPriceBycartId(@Param("cartId") String cartId);
}
