package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.mapper.LoginMapper;
import com.zunke.shopmanager.mapper.MumberDTOMapper;
import com.zunke.shopmanager.pojo.Mumber;
import com.zunke.shopmanager.service.LoginService;
import com.zunke.shopmanager.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuzhe
 * @version 1.0
 * @since 2021-09-13-16:25
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private MumberDTOMapper mumberDTOMapper;

    @Override
    public Map<String, Object> login(UserVO userVO, HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 600);
        map.put("msg", "用户名或者密码错误!");
        if(userVO.getMumberName() == null || "".equals(userVO.getMumberName())){
            return map;
        }

        Mumber mumber = new Mumber();
        BeanUtils.copyProperties(userVO, mumber);
        Mumber mum = loginMapper.login(mumber);
        if(mum == null || mum.getMumberPassword() == null){
            return map;
        }

        //将用户输入的密码加密
        String md5Password = DigestUtils.md5DigestAsHex(mumber.getMumberPassword().getBytes());
        //比较密文
        if((mum.getRoleId() == 2 || mum.getRoleId() == 3)
                && md5Password.equals(mum.getMumberPassword())) {
            HttpSession session = request.getSession();

            String token = UUID.randomUUID().toString().replaceAll("-","");
            mumberDTOMapper.updateUuid(mum.getId(),token);
//            System.out.println(mumberDTOMapper.selectById(mum.getId()).getMumberUuid());
//            response.setHeader("token", mumberDTOMapper.selectById(mum.getId()).getMumberUuid());
            response.setHeader("token", token);

            session.setAttribute("token", token);
            session.setAttribute("user",mum);
            //session30秒就失效
            //session.setMaxInactiveInterval(1*30);
            map.put("token", token);
            map.put("code", 200);
            map.put("msg", "登录成功!");
            map.put("data", mum);
        }
        return map;
    }


    @Override
    public Map<String, Object> phoneLogin(String phone, String phoneCode, HttpSession session, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 600);
        map.put("msg", "手机号或者验证码错误!");

        Mumber mumber = new Mumber();
        mumber.setMumberPhone(phone);
        Mumber mumberReturn = loginMapper.login(mumber);
        if (mumberReturn == null || mumberReturn.getPhoneCode() == null) {
            return map;
        }
        if(0 == mumberReturn.getMumberAction()){
            map.put("code", 600);
            map.put("msg", "账户未激活!");
            return map;
        }
        long nowTime = System.currentTimeMillis();
        long sendTime = mumberReturn.getSendTime().getTime();
        if((nowTime - sendTime) > 1*60*5000){
            map.put("code", 601);
            map.put("msg", "验证码已失效!");
            return map;
        }
        if(mumberReturn.getPhoneCode().equals(phoneCode)) {
            loginMapper.updateByPhone("",null,phone);
            String token = UUID.randomUUID().toString().replaceAll("-","");

            mumberDTOMapper.updateUuid(mumberReturn.getId(),token);

            response.setHeader("token", token);
            session.setAttribute("token", token);
            session.setAttribute("user", mumberReturn);

            map.put("code", 200);
            map.put("msg", "登录成功!");
            map.put("data", mumberReturn);
            map.put("token", token);
        }
        return map;
    }

    @Override
    public Map<String, Object> sendCode(String phone) {
        int phoneCode = (int)((Math.random() * 9 + 1) * 100000);

        //String phoneMsg = phoneCode +",**minute**:1";
//        String host = "https://gyytz.market.alicloudapi.com";
//        String path = "/sms/smsSend";
//        String method = "POST";
//        String appcode = "38a92447bb99sd4dsvd45wewf23b9969";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE
//        headers.put("Authorization", "APPCODE " + appcode);
//        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("mobile", phone);
//        querys.put("param", "**code**:"+phoneMsg);
//        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
//        querys.put("templateId", "02551a4313154fe4805794ca069d70bf");
//        Map<String, String> bodys = new HashMap<String, String>();

        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("code", 500);
        objectObjectHashMap.put("msg", "发送失败");

        loginMapper.updateByPhone("" + phoneCode, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), phone);
        objectObjectHashMap.put("code", 200);
        objectObjectHashMap.put("msg", "发送成功");

//        try {
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
//            //获取response的body
////            System.out.println(EntityUtils.toString(response.getEntity()));
//            String string = EntityUtils.toString(response.getEntity());
//            JSONObject jsonObject = new JSONObject(string);
//            // 解析返回的json对象，判断是否发送成功，如果发送成功，则更新当前用户表中的验证码内容
//            String code = jsonObject.getString("code");
//            if("0".equals(code)) {
//                loginMapper.updateByPhone("" + phoneCode, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), phone);
//                objectObjectHashMap.put("code", 200);
//                objectObjectHashMap.put("msg", "发送成功");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return objectObjectHashMap;
    }
}
