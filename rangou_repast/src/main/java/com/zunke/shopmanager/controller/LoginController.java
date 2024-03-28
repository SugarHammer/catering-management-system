package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.service.LoginService;
import com.zunke.shopmanager.vo.UserVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhe
 * @version 1.0
 * @since 2021-09-13-15:56
 */

@RestController
@RequestMapping("/user")
@Api(value = "后台管理登录接口",tags = "后台管理登录接口API")
@ApiResponses({
    @ApiResponse(code=400,message="请求参数不完整或者错误"),
    @ApiResponse(code=404,message="找不到页面"),
    @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
    @ApiResponse(code=406,message="页面地址和接口名冲突"),
    @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
    @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class LoginController {
    /**
     * 登录
     * @param mumber
     * @param request
     * @return
     */
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "后台管理员登录接口",notes = "后台管理员登录接口",httpMethod = "POST")
    public Map<String, Object> login(@RequestBody @ApiParam(name = "userVO",value = "vo模型",required = true)UserVO userVO,
                                     @ApiIgnore HttpServletRequest request,
                                     @ApiIgnore HttpServletResponse response){
        Map<String, Object> map = loginService.login(userVO, request,response);
        return map;
    }

    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    @ApiOperation(value = "后台管理员退出接口",notes = "后台管理员退出接口",httpMethod = "get")
    public Map<String, Object> loginout(HttpServletRequest request){
        request.getSession().invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "退出成功");
        return map;
    }

    @PostMapping("/phoneLogin")
    @ApiOperation(value = "手机验证码登录接口",notes = "手机验证码登录接口",httpMethod = "POST")
    public Map<String, Object> phoneLogin(@RequestParam("phone")String phone,
                                          @RequestParam("phoneCode")String phoneCode,
                                          @ApiIgnore HttpSession session,
                                          @ApiIgnore HttpServletResponse response){
        return loginService.phoneLogin(phone, phoneCode, session,response);
    }

    @PostMapping("/sendCode")
    @ApiOperation(value = "手机验证码发送接口",notes = "手机验证码发送接口",httpMethod = "Post")
    public Map<String, Object> sendCode(@RequestParam String phone) {
        return loginService.sendCode(phone);
    }

}
