package com.zunke.shopmanager.mapper;

import com.zunke.shopmanager.dto.MumberRole;
import com.zunke.shopmanager.pojo.Permission;
import com.zunke.shopmanager.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-14 10:13
 */
public interface RoleMapper {

    /**
     * 通过用户主键查询单条数据
     *
     * @param id    用户主键
     * @return      实例对象
     */
    Role selectById(@Param("id") Integer id);


    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    List<Role> selectAll();

    /**
     * 修改角色权限
     *
     * @param role  角色对象
     * @return      实例对象
     */
    int updateByName(Role role);

    /**
     * 修改用户角色
     * @param roleId    权限主键
     * @param id        用户主键
     * @return          修改结果
     */
    int updateRole(@Param("roleId") Integer roleId,@Param("id")Integer id);


    /**
     * 修改用户资料
     *
     * @param mumberRole    用户的dto对象
     * @return              修改结果
     */
    int updateByMumber(MumberRole mumberRole);

//    int updateByMumber(@Param("mumberName") String mumberName, @Param("mumberPhone")String mumberPhone,
//                       @Param("mumberPassword")String mumberPassword,@Param("id") int id);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param index         查询起始位置
     * @param roleName      查询条件
     * @return 对象列表
     */
    List<Permission> selectForPage(@Param("index") int index, @Param("roleName")String roleName);

    /**
     * 根据模糊条件查询总个数
     * @param roleName      角色名
     * @return              满足条件的数量
     */
    int selectForCount(@Param("roleName") String roleName);

    /**
     * 通过一个dto对象新增单条数据
     *
     * @param mumberRole 一个数据库对应的dto数据对象
     */
    void insert(MumberRole mumberRole);
}
