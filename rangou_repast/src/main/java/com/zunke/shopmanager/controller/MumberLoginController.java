package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.dto.MumberDTO;
import com.zunke.shopmanager.email.MyJavaEmail;
import com.zunke.shopmanager.service.impl.MumberLoginServiceImpl;
import com.zunke.shopmanager.vo.MumberVO;
import io.swagger.annotations.*;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-21 16:27
 */

@RestController
@RequestMapping("/login")
@Api(value = "前台账户登录验证",tags = "前台账户登录验证接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class MumberLoginController {

    @Resource
    private MumberLoginServiceImpl mumberLoginService;

    /**
     * 通过账号密码登录
     * @param session   一个session对象
     * @param mumberVO  一个VO对象
     * @return          返回登录结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "账户密码登录验证接口",notes = "账户密码登录验证接口 必须传递password和username",httpMethod = "POST")
    public Map<String, Object> login(@ApiIgnore HttpServletResponse response,
                                     @ApiIgnore HttpSession session,
                                     @RequestBody MumberVO mumberVO){
        return mumberLoginService.login(mumberVO, response,session);
    }

    /**
     * 通过手机号及其验证码登录
     * @param phone         登录的手机号
     * @param phoneCode     验证码
     * @param session       一个session对象
     * @return              返回登录结果
     */
    @PostMapping("/phoneLogin")
    @ApiOperation(value = "短信验证登录接口",notes = "短信验证登录接口 必须传递phone和phoneCode",httpMethod = "POST")
    public Map<String, Object> phoneLogin(@RequestParam String phone,
                                          @RequestParam String phoneCode,
                                          @ApiIgnore HttpSession session,
                                          @ApiIgnore HttpServletResponse response){
        return mumberLoginService.phoneLogin(phone,phoneCode,session,response);
    }

    /**
     * 通过手机号及其密码登录
     * @param phone         登录的手机号
     * @param password      登录密码
     * @param session       一个session对象
     * @return              返回登录结果
     */
    @PostMapping("/phonePasswordLogin")
    @ApiOperation(value = "手机号密码登录接口",notes = "手机号密码登录接口 必须传递phone和password",httpMethod = "POST")
    public Map<String, Object> phonePasswordLogin(@RequestParam String phone,
                                                  @RequestParam String password,
                                                  @ApiIgnore HttpSession session,
                                                  @ApiIgnore HttpServletResponse response){
        return mumberLoginService.phonePasswordLogin(phone,password,session,response);
    }

    /**
     * 发送一个验证码
     * @param phone     需要发验证码的手机号
     * @return          返回发送结果
     */
    @PostMapping("/sendCode")
    @ApiOperation(value = "通过电话更新一个验证码的接口",notes = "通过电话更新一个验证码的接口",httpMethod = "POST")
    public Map<String, Object> sendCode(@RequestParam String phone){
        return mumberLoginService.sendCode(phone);
    }

    /**
     * 通过邮箱注册账号
     * @param mumberDTO                 mumber的DTO对象
     * @return                          返回注册结果
     * @throws InterruptedException     邮件发送中断
     */
    @PostMapping("/registration")
    @ApiOperation(value = "注册激活",notes = "注册激活",httpMethod = "POST")
    public Map<String, Object> registration(@RequestBody @ApiParam(name = "user",value = "DTO模型",required = true)
                                                    MumberDTO mumberDTO) throws InterruptedException {

        // java系统默认生成的uuid，但是默认的uuid是有横杠隔开的，项目中需要手动去掉横杠
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        Thread thread = new Thread(new MyJavaEmail(mumberDTO.getMumberName(),mumberDTO.getMumberEmail(),uuid));
        thread.start();
        thread.join();  //等待所有子线程执行完毕
        mumberDTO.setMumberUuid(uuid);
        // 新增用户数据库字段信息
        this.mumberLoginService.insert(mumberDTO);
        // 发送邮件
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "请到邮箱激活！");
        // 返回信息提示用户到邮件激活
        return map;
    }

    /**
     * 邮箱验证
     * @param mumberUuid    每个用户的唯一识别码
     * @return              返回激活结果
     */
    @ApiOperation(value = "验证激活",notes = "验证激活",httpMethod = "get")
    @GetMapping("/jiHuo")
    public Map<String, Object> jiHuo(@RequestParam String mumberUuid) {   // 当用户点击激活地址，会调用指定接口，此接口会接收到uuid值
        // 通过uuid检查用户账户是否存在，如果存在，则更新用户的状态为1，代表激活
        return mumberLoginService.selectByUuid(mumberUuid);
    }

    /**
     * 头像上传
     * @param file      需要上传的文件
     * @param id        上传的文件和哪个用户绑定
     * @param request   request对象
     * @return          返回执行结果
     * @throws IOException  IO异常
     */
    @PostMapping("/pictureUpload")
    @ApiOperation(value = "头像上传接口",notes = "头像上传接口",httpMethod = "POST")
    public Map<String, Object> pictureUpload(MultipartFile file,String id, HttpServletRequest request) throws IOException {
        return mumberLoginService.pictureUpload(file,id, request);
    }

    /**
     * 前台系统退出
     * @param request   request对象
     * @return          返回执行结果
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ApiOperation(value = "前台会员退出接口",notes = "前台会员退出接口",httpMethod = "get")
    public Map<String, Object> logout(HttpServletRequest request){
        request.getSession().invalidate();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "退出成功");
        return map;
    }

    /**
     * 修改用户资料
     * @param mumberDTO     dto对象
     * @param request       request对象
     * @return              更新结果
     */
    @RequestMapping(value = "/updateMumber", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户资料的接口",notes = "修改用户资料的接口",httpMethod = "POST")
    public Map<String, Object> updateMumber(@RequestBody @ApiParam(name = "mumber",value = "mumber模型",required = true)
                                                        MumberDTO mumberDTO,HttpServletRequest request) {
        return this.mumberLoginService.updateMumber(mumberDTO,request);
    }

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过主键查询的接口",notes = "通过主键查询的接口",httpMethod = "GET")
    public Map<String, Object> selectById(Integer id) {
        return this.mumberLoginService.selectById(id);
    }

    /**
     * 修改用户密码
     * @param id            用户的id
     * @param oldPassword   原密码
     * @param password      新密码
     * @return              返回结果
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户密码的接口",notes = "修改用户密码的接口",httpMethod = "POST")
    public Map<String, Object> updatePassword(@RequestParam Integer id,@RequestParam String oldPassword,@RequestParam String password) {
        return this.mumberLoginService.updatePassword(id,oldPassword,password);
    }

    /**
     * 忘记密码
     * @param phone         手机号
     * @param phoneCode     验证码
     * @param password      新密码
     * @return              返回执行结果
     */
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ApiOperation(value = "忘记密码的接口",notes = "忘记密码的接口",httpMethod = "POST")
    public Map<String, Object> forgetPassword(@RequestParam String phone,@RequestParam String phoneCode,@RequestParam String password,@ApiIgnore HttpSession session) {
        mumberLoginService.sendCode(phone);
        return mumberLoginService.forgetPassword(phone,phoneCode,password);
    }
}
