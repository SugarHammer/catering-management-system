package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.Util.HttpUtils;
import com.zunke.shopmanager.dto.MumberDTO;
import com.zunke.shopmanager.mapper.MumberDTOMapper;
import com.zunke.shopmanager.vo.MumberVO;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-21 16:53
 */

@Service("mumberLoginService")
public class MumberLoginServiceImpl {
    @Resource
    private MumberDTOMapper mumberDTOMapper;

    /**
     * 通过账号密码登录
     * @param session   一个session对象
     * @param mumberVO  一个VO对象
     * @return          返回登录结果
     */
    public Map<String, Object> login(MumberVO mumberVO, HttpServletResponse response,HttpSession session){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 601);
        map.put("msg", "用户名或者密码错误!");

        MumberDTO mumber = new MumberDTO();
        BeanUtils.copyProperties(mumberVO, mumber);

        MumberDTO return_user = mumberDTOMapper.login(mumber.getMumberName());
        String md5Password = DigestUtils.md5DigestAsHex(mumber.getMumberPassword().getBytes());
        if(return_user != null
                && (return_user.getRoleId() == 1 || return_user.getRoleId() == 4)
                && return_user.getMumberPassword().equals(md5Password)) {
            map.put("code", 601);
            map.put("msg", "该账户未激活!");
            if (return_user.getMumberAction() != 0){
                String token = UUID.randomUUID().toString().replaceAll("-","");

                mumberDTOMapper.updateUuid(return_user.getId(),token);

                map.put("code", 200);
                map.put("msg", "登录成功!");
                map.put("data", return_user);
                response.setHeader("token", token);
                session.setAttribute("user", return_user);

//                最后登录时间
//                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//                mumber.set(new Date());
//                mumberMapper.updateByUserName(mumber);

            }
        }
        return map;
    }


    /**
     * 短信验证码的发送
     * @param phone     手机号
     * @return          返回结果
     */
    public Map<String, Object> sendCode(String phone) {

        String host = "https://intlsms.market.alicloudapi.com";
        String path = "/comms/sms/sendmsgall";
        String method = "POST";
        String appcode = "4a44804bd7e144138ee11876b5a46bf1";
        //判断该手机号是否存在
        if (mumberDTOMapper.phoneLogin(phone) == null){
            String md5Password = DigestUtils.md5DigestAsHex("111111".getBytes());
            mumberDTOMapper.insertByPhone(phone,md5Password);
        }
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();

        // 生成6位验证码
        int phoneCode = (int) ((Math.random() * 9 + 1) * 100000);

        bodys.put("callbackUrl", "http://test.dev.esandcloud.com");
        bodys.put("channel", "0");
        bodys.put("mobile", "+86" + phone);
        bodys.put("templateID", "0000000");
        bodys.put("templateParamSet", phoneCode + ", 5");

        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "发送失败");


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            String string = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject(string);
            // 解析返回的json对象，判断是否发送成功，如果发送成功，则更新当前用户表中的验证码内容
            // code为0000时，代表发送成功
            String code = jsonObject.getString("code");
            // 四个0的字符串代表成功，如果成功，更新数据库用户验证码字段
            if("0000".equals(code)) {
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                mumberDTOMapper.updateByPhone("" + phoneCode,format, phone);
                map.put("code", 200);
                map.put("msg", "发送成功");
            }
            System.out.println(phoneCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 通过手机号及其验证码登录
     * @param phone         登录的手机号
     * @param phoneCode     验证码
     * @param session       一个session对象
     * @return              返回登录结果
     */
    public Map<String, Object> phoneLogin(String phone, String phoneCode, HttpSession session,HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 601);
        map.put("msg", "验证码错误!");

        // 验证用户名是，已经返回了完整的用户信息数据，这里面包含了验证码发送时间
        MumberDTO return_user = mumberDTOMapper.phoneLogin(phone);
        if (return_user == null) {
            map.put("code", 601);
            map.put("msg", "此账户不存在!");
            return map;
        }
        // 获取当前时间的毫秒数
        long nowTime = System.currentTimeMillis();
        // 得到验证码发送时间的毫秒数
        long sendTime = return_user.getSendTime().getTime();
        // 计算出两个时间毫秒数的差值
        long cha = nowTime - sendTime;
        // 判断差值是否与了5分钟   5 * 60 * 1000
        // 如果差值大于了1分钟，则直接返回错误提示
        if(cha > 5 * 60 * 1000L) {
            map.put("code", 601);
            map.put("msg", "验证码已失效!");
            return map;
        }
        if(return_user.getPhoneCode().equals(phoneCode)) {
//            登录成功后删除验证码
            mumberDTOMapper.updateByPhone( "","", phone);
            String token = UUID.randomUUID().toString().replaceAll("-","");

            mumberDTOMapper.updateUuid(return_user.getId(),token);

            map.put("code", 200);
            map.put("msg", "登录成功!");
            map.put("data", return_user);
            response.setHeader("token", token);
            session.setAttribute("user", return_user);
        }
        return map;
    }


    /**
     * 注册新增数据
     *
     * @param mumberDTO    注册的对象
     * @return             实例对象
     */
    public Map<String, Object> insert(MumberDTO mumberDTO) {
        //设置注册会员时间
        Date date = new Date();
        mumberDTO.setMumberRergistrationTime(date);

        String mumberPassword = mumberDTO.getMumberPassword();
        String md5Password = DigestUtils.md5DigestAsHex(mumberPassword.getBytes());
        mumberDTO.setMumberPassword(md5Password);

        this.mumberDTOMapper.insertByRergistration(mumberDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }

    /**
     *
     * @param mumberUuid    唯一识别码
     * @return              返回信息提示用户已激活
     */
    public Map<String, Object> selectByUuid(String mumberUuid) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 601);
        map.put("msg", "激活失败!");

        MumberDTO returnMumberDTO = mumberDTOMapper.selectByUuid(mumberUuid);
        if(returnMumberDTO != null && returnMumberDTO.getMumberUuid().equals(mumberUuid)){
            mumberDTOMapper.updateByUuid(mumberUuid);
            map.put("code", 200);
            map.put("msg", "激活成功,请返回登录!");
        }
        return map;
    }


    /**
     * 头像上传
     * @param file      需要上传的文件
     * @param id        上传的文件和哪个用户绑定
     * @param request   request对象
     * @return          返回执行结果
     * @throws IOException  IO异常
     */
    public Map<String, Object> pictureUpload(MultipartFile file,String id, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String abPath = "upload";
        // 得到相当项目工程的硬盘物理地址
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + abPath + File.separator;
        // 获取上传的文件名
        String fileName = file.getOriginalFilename();
        // 根据当前工程的物理路径+文件名生成一个file对象
        File newFile = new File(uploadPath + fileName);
        System.out.println(newFile);
        // 通过上传通道中的字节码完成file文件的写入操作
        FileCopyUtils.copy(file.getBytes(), newFile);

        // 组装一个浏览器识别的文件地址
        String userSQLPath = File.separator + abPath + File.separator + fileName;
        mumberDTOMapper.pictureUpload(userSQLPath, id);
        map.put("code", 200);
        map.put("msg", "上传头像成功");
        map.put("data", userSQLPath);
        return map;
    }

    /**
     * 修改用户资料
     * @param mumberDTO     dto对象
     * @param request       request对象
     * @return              更新结果
     */
    public Map<String, Object> updateMumber(MumberDTO mumberDTO, HttpServletRequest request) {
        String mumberPassword = mumberDTO.getMumberPassword();
        //判断密码是否为null或空串
        if (mumberPassword != null){
            //得到新密码的密文
            String md5Password = DigestUtils.md5DigestAsHex(mumberPassword.getBytes());
            mumberDTO.setMumberPassword(md5Password);
        }
        this.mumberDTOMapper.updateMumber(mumberDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "更新成功");
        return map;
    }

    /**
     * 通过手机号及其密码登录
     * @param phone         登录的手机号
     * @param password      登录密码
     * @param session       一个session对象
     * @return              返回登录结果
     */
    public Map<String, Object> phonePasswordLogin(String phone, String password, HttpSession session,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 601);
        map.put("msg", "密码错误!");

        // 验证用户名是，已经返回了完整的用户信息数据，这里面包含了验证码发送时间
        MumberDTO return_user = mumberDTOMapper.phoneLogin(phone);
        if (return_user == null) {
            map.put("code", 601);
            map.put("msg", "此账户不存在!");
            return map;
        }
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if((return_user.getRoleId() == 1 || return_user.getRoleId() == 4)
                && return_user.getMumberPassword().equals(md5Password)) {
            String token = UUID.randomUUID().toString().replaceAll("-","");

            mumberDTOMapper.updateUuid(return_user.getId(),token);

            map.put("code", 200);
            map.put("msg", "登录成功!");
            map.put("data", return_user);
            response.setHeader("token", token);
            session.setAttribute("user", return_user);
        }
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Map<String, Object> selectById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.mumberDTOMapper.selectById(id));
        return map;
    }

    /**
     * 密码修改
     * @param id    用户id
     * @param oldPassword   原密码
     * @param password      新密码
     * @return              结果
     */
    public Map<String, Object> updatePassword(Integer id,String oldPassword, String password) {

        //得到原密码的密文
        String oldMd5Password = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        //得到新密码的密文
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        //通过id查到该用户的密码密文
        String mumberPassword = mumberDTOMapper.selectById(id).getMumberPassword();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 601);
        map.put("msg", "原密码错误");
        //判断原密码是否一致
        if (mumberPassword.equals(oldMd5Password)){
            this.mumberDTOMapper.updatePassword(id,md5Password);
            map.put("code", 200);
            map.put("msg", "密码修改成功");
            return map;
        }
        return map;
    }


    /**
     * 忘记密码
     * @param phone         手机号
     * @param phoneCode     验证码
     * @param password      新密码
     * @return              返回执行结果
     */
    public Map<String, Object> forgetPassword(String phone, String phoneCode, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 601);
        map.put("msg", "验证码错误!");

        // 验证用户名是，已经返回了完整的用户信息数据，这里面包含了验证码发送时间
        MumberDTO return_user = mumberDTOMapper.phoneLogin(phone);
        if (return_user == null) {
            map.put("code", 601);
            map.put("msg", "此账户不存在!");
            return map;
        }
        // 获取当前时间的毫秒数
        long nowTime = System.currentTimeMillis();
        // 得到验证码发送时间的毫秒数
        long sendTime = return_user.getSendTime().getTime();
        // 计算出两个时间毫秒数的差值
        long cha = nowTime - sendTime;
        // 判断差值是否与了5分钟   5 * 60 * 1000
        // 如果差值大于了1分钟，则直接返回错误提示
        if(cha > 5 * 60 * 1000L) {
            map.put("code", 601);
            map.put("msg", "验证码已失效!");
            return map;
        }
        if(return_user.getPhoneCode().equals(phoneCode)) {
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            mumberDTOMapper.updatePassword(return_user.getId(), md5Password);
            map.put("code", 200);
            map.put("msg", "修改成功!");
            map.put("data", return_user);
        }
        return map;
    }
}
