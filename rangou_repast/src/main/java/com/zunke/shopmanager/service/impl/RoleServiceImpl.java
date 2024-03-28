package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.MumberRole;
import com.zunke.shopmanager.mapper.RoleMapper;
import com.zunke.shopmanager.pojo.Permission;
import com.zunke.shopmanager.pojo.Role;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ZhangHongNeng
 * @version ：1.0
 * @since 2021-09-14 10:08
 */
@Service("roleService")
public class RoleServiceImpl {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 通过角色名称查询单条数据
     *
     * @param id    用户主键
     * @return      实例对象
     */
    public Map<String, Object> selectById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.roleMapper.selectById(id));
        return map;
    }

    /**
     * 查询所有数据
     * @return  返回所有数据
     */
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.roleMapper.selectAll());
        return map;
    }

    /**
     * 修改权限
     *
     * @param role  实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateByName(Role role) {
        this.roleMapper.updateByName(role);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "更新成功");
        return map;
    }

    /**
     * 修改权限
     * @param roleId    权限主键
     * @param id        用户主键
     * @return          更新结果
     */
    public Map<String, Object> updateRole(Integer roleId,Integer id) {
        this.roleMapper.updateRole(roleId,id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "更新成功");
        return map;
    }

    /**
     * 修改用户资料
     *
     * @param mumberRole  实例对象
     * @return 实例对象
     */
    public Map<String, Object>updateByMumber(MumberRole mumberRole) {
        String mumberPassword = mumberRole.getMumberPassword();
        if (mumberPassword != null){
            String md5Password = DigestUtils.md5DigestAsHex(mumberPassword.getBytes());
            mumberRole.setMumberPassword(md5Password);
        }

        this.roleMapper.updateByMumber(mumberRole);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "更新成功");
        return map;
    }

//    /**
//     * 修改用户资料
//     *
//     * @param mumberRole  实例对象
//     * @return 实例对象
//     */
//    public Map<String, Object> updateByMumber(String mumberName, String mumberPhone,String mumberPassword,int id) {
//        this.roleMapper.updateByMumber(mumberName,mumberPhone,mumberPassword,id);
//        Map<String, Object> map = new HashMap<>();
//        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
//        map.put("code", 200);
//        map.put("msg", "更新成功");
//        return map;
//    }

    /**
     * 通过主键删除账户数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String id) {
        this.roleMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @return      对象列表
     */
    public Map<String, Object> selectForPage(int index, String name) {
        // 获取当前表中的总记录
        int tableCount = this.roleMapper.selectForCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / 10 + 1;
        // 计算每页开始的下标值
        index = (index - 1) * 10;
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("pageCount", pageCount);  // 查询的记录总页码
        map.put("count", tableCount);     // 当前表中的总条数
        map.put("data", this.roleMapper.selectForPage(index, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param mumberRole 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(MumberRole mumberRole) {

        String mumberPassword = mumberRole.getMumberPassword();
        String md5Password = DigestUtils.md5DigestAsHex(mumberPassword.getBytes());
        mumberRole.setMumberPassword(md5Password);

        this.roleMapper.insert(mumberRole);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "新增成功");
        return map;
    }
}
