package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.pojo.Mumber;
import org.apache.ibatis.annotations.Param;

/**
 * @author yuzhe
 * @version 1.0
 * @since 2021-09-13-16:08
 */
public interface LoginMapper {

    /**
     * 通过用户名或手机号查询
     * @param mumber 用户对象
     * @return       返回一个用户对象
     */
    Mumber login(Mumber mumber);

    /**
     * 基于用户的手机更新验证码
     * @param phoneCode  验证码
     * @param phone      手机号
     * @return           更新状态
     */
    int updateByPhone(@Param("phoneCode") String phoneCode, @Param("sendTime") String sendTime, @Param("phone") String phone);

}
