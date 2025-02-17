package com.zunke.shopmanager.email;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-21 13:28
 */
public class MyJavaEmail implements Runnable {

    private String username;          // 登录的用户名
    private String email;             // 收件人邮箱
    private String uuid;              // uuid唯一码

    public MyJavaEmail(String username, String email, String uuid) {
        this.username = username;
        this.email = email;
        this.uuid = uuid;
    }

    @Override
    public void run() {
        // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)  这里需要继续扩展做判断分支处理
        String host = "smtp.qq.com";

        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 打开认证
        properties.setProperty("mail.smtp.auth", "true");
        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EmailFiled.EmailName, EmailFiled.EmailCode); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);

            // 3.设置发件人
            message.setFrom(new InternetAddress(EmailFiled.EmailName));

            // 4.设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // 5.设置邮件主题
            message.setSubject("码农社区账号激活");

            // 6.设置邮件内容
            String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接，如果不是本人操作，请勿点击</h1><h3>" +
                    "<a href='http://localhost:8080/jiHuo?uuid=" + uuid + "'>" +
                    "http://localhost:8080/jiHuo?uuid=" + uuid
                    + "</a></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");

            // 7.发送邮件
            // 阻塞方法
            Transport.send(message);
            System.out.println("邮件成功发送!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 提供为外部调用的邮件方法  应该把此方法提出来放置到service服务中
     * @return  返回状态true为成功，false为失败
     */
    public boolean checkRegister() {
        // 没有判断邮箱和用户名是否为空，需要额外增加代码处理
        if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){          // 利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
            return false;
        }

//        User user = new User(username, password, email, uuid);        // 写入用户POJO
//        UserDao userDao = new UserDaoImpl();                          // 将用户保存到数据库
//        if(userDao.save(user) > 0){                                   // 保存成功则通过线程的方式给用户发送一封邮件
        new Thread(new MyJavaEmail(username,email,uuid)).start();       // 启动一个子线程发送邮件，第一个参数为接收账号，第二参数为随机码
//            return true;
//        }
        return false;
    }



//    public static void main(String[] args) {
//        // pop3协议如果权限认证失败，会报535错误
//        // 启动一个子线程发送邮件，第一个参数为发送方用户账户，第二参数为被接收的邮件地址
//        new Thread(new MyJavaEmail("゛北岛旅客つ","1605307326@qq.com")).start();
////        System.out.println(UUID.randomUUID().toString().replace("-",""));
//    }
}
