package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.MumberDTO;
import com.zunke.shopmanager.pojo.Permission;
import org.apache.ibatis.annotations.Param;


/**
 * 餐桌表(Dining)表数据库访问层
 *
 * @author ZhangHongNeng
 * @since 2021-09-23 11:20:02
 * @version 1.0
 */
public interface MumberDTOMapper {

    /**
     * 用户通过邮箱注册
     * @param mumberDTO    实例对象
     */
    void insertByRergistration(MumberDTO mumberDTO);

    /**
     * 通过用户的唯一识别码查询该用户
     *
     * @param mumberUuid    注册时的唯一识别码
     * @return              实例对象
     */
    MumberDTO selectByUuid(String mumberUuid);

    /**
     * 点击激活后将用户状态改为启用
     *
     * @param mumberUuid 实例对象
     * @return 影响行数
     */
    int updateByUuid(String mumberUuid);

    /**
     * 用户名登录时查询用户是否存在
     * @param mumberName    用户名
     * @return              返回该用户实例对象
     */
    MumberDTO login(String mumberName);

    /**
     * 短信验证码的存储
     * @param phoneCode     手机验证码
     * @param sendTime      发送验证码的时间
     * @param mumberPhone   手机号
     */
    void updateByPhone(@Param("phoneCode") String phoneCode,@Param("sendTime") String sendTime,
                       @Param("mumberPhone") String mumberPhone);

    /**
     * 手机账户登录时查询该手机号是否存在
     * @param mumberPhone   手机号
     * @return              返回该用户实例对象
     */
    MumberDTO phoneLogin(String mumberPhone);

    /**
     * 手机号登录时注册
     * @param mumberPhone   手机号
     */
    void insertByPhone(@Param("mumberPhone") String mumberPhone,@Param("mumberPassword") String md5Password);

//    /**
//     * 通过手机号查询单条数据
//     *
//     * @param phone 手机号码
//     * @return 实例对象
//     */
//    MumberDTO selectByPhone(@Param("mumberPhone") String phone);

//    /**
//     * 最后登录时间
//     * @param return_user   登录的对象
//     */
//    void updateByUserName(Mumber return_user);

//    修改最后登录时间
//    void updateByUserName(Mumber mumber);

    /**
     * 修改头像地址
     * @param mumberHeadImg     头像地址
     * @param id                用户
     */
    void pictureUpload(@Param("mumberHeadImg") String mumberHeadImg,@Param("id") String id);

    /**
     * 修改用户的资料
     * @param mumberDTO     该用户实例对象
     */
    void updateMumber(MumberDTO mumberDTO);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MumberDTO selectById(@Param("id") Integer id);

    /**
     * 密码修改
     * @param id    用户id
     * @param md5Password   加密后的新密码
     */
    void updatePassword(@Param("id") Integer id, @Param("md5Password") String md5Password);

    void updateUuid(@Param("id") Integer id, @Param("mumberUuid") String token);
}
