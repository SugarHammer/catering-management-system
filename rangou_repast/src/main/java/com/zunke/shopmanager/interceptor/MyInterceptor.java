package com.zunke.shopmanager.interceptor;

import com.zunke.shopmanager.dto.MumberDTO;
import com.zunke.shopmanager.mapper.MumberDTOMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Resource
    private MumberDTOMapper mumberDTOMapper;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletResponse.getHeader("token");
        System.out.println(token);
        MumberDTO mumberDTO = mumberDTOMapper.selectByUuid(token);
        if(token == null) {

            httpServletResponse.sendRedirect("/login");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
