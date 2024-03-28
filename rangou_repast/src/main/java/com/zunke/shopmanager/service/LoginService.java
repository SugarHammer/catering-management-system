package com.zunke.shopmanager.service;

import com.zunke.shopmanager.vo.UserVO;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yuzhe
 * @version 1.0
 * @since 2021-09-13-16:24
 */
public interface LoginService {
    /**
     * 用户登录
     * @param userVO        vo模型，传入用户名和密码
     * @param request       请求对象
     * @return              返回登录是否成功的map对象
     */
    Map<String, Object> login(UserVO userVO, HttpServletRequest request,HttpServletResponse response);

    /**
     * 手机验证码登录
     * @param phone       手机号
     * @param phoneCode   验证码
     * @param session     session对象
     * @return           返回登录是否成功的map对象
     */
    Map<String, Object> phoneLogin(String phone, String phoneCode, HttpSession session,HttpServletResponse response);


    /**
     * 手机验证码发送
     * @param phone      手机号
     * @return           返回验证码发送是否成功的map对象
     */
    Map<String, Object> sendCode(String phone);
}
