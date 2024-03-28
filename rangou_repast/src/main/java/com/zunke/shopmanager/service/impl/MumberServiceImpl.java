package com.zunke.shopmanager.service.impl;

import com.zunke.shopmanager.dto.RegisterNumber;
import com.zunke.shopmanager.mapper.MumberMapper;
import com.zunke.shopmanager.pojo.Mumber;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会员表(Mumber)表服务实现类
 *
 * @author hy
 * @version 1.0
 * @since 2021-09-15 08:44:10
 */

@Service("mumberService")
public class MumberServiceImpl {
    @Resource
    private MumberMapper mumberMapper;

    /**
     * 根据模糊条件查询总个数
     *
     * @param name 查询条件
     * @return 返回查询到的总个数
     */
    public Map<String, Object> selectForCount(String name) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.mumberMapper.selectForCount(name));
        return map;
    }

    /**
     * 查询所有数据
     *
     * @return 返回所有数据
     */
    public Map<String, Object> selectAll(Mumber mumber) {
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.mumberMapper.selectAll(mumber));
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
        map.put("data", this.mumberMapper.selectById(id));
        return map;
    }

    /**
     * 查询当天注册人数总计
     *
     * @param
     * @return 实例对象
     */
    public Map<String, Object> selectTodayRegisterPerson() {
        Map<String, Object> map = new HashMap<>();

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        map.put("data", this.mumberMapper.selectTodayRegisterPerson(time));
        return map;
    }

    /**
     * 查询分页数据
     *
     * @param index 查询起始位置
     * @param name  查询条件
     * @param limit 分页数量
     * @return 对象列表
     */
    public Map<String, Object> selectForPage(int index, int limit, String name) {
        // 获取当前表中的总记录
        int tableCount = this.mumberMapper.selectForCount(name);
        // 总页码计算   (总条数 - 1) / 每页显示条数  + 1
        // (100 - 1) / 10 + 1 = 10        (101 - 1) / 10 + 1 = 11      (99 - 1) / 10 + 1 = 10
        int pageCount = (tableCount - 1) / limit + 1;
        // 计算每页开始的下标值
        index = (index - 1) * limit;
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "查询成功");
        // 查询的记录总页码
        map.put("pageCount", pageCount);
        // 当前表中的总条数
        map.put("count", tableCount);
        map.put("data", this.mumberMapper.selectForPage(index, limit, name));
        return map;
    }

    /**
     * 新增数据
     *
     * @param mumber 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(Mumber mumber) {

        //设置注册会员时间
        Date date = new Date();
        mumber.setMumberRergistrationTime(date);
        mumber.setRoleId(0);
        System.out.println(mumber);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "新增失败,该号码已被注册");
        if (mumberMapper.selectByPhone(mumber.getMumberPhone())==null){
            this.mumberMapper.insert(mumber);
            // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
            map.put("code", 200);
            map.put("msg", "新增成功");
        }
        return map;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param mumber 实例对象
     * @return 实例对象
     */
    public Map<String, Object> updateById(Mumber mumber) {
        this.mumberMapper.updateById(mumber);
        Map<String, Object> map = new HashMap<>();
        Mumber mumber1 = mumberMapper.selectById(mumber.getId());
        if(mumber1.getMumberPhone()== mumber.getMumberPhone()||mumberMapper.selectByPhone(mumber.getMumberPhone())==null){
            this.mumberMapper.updateById(mumber);
            // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
            map.put("code", 200);
            map.put("msg", "更新成功");
        }
        return map;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String id) {
        this.mumberMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        // 前端端分离时，前端人员会首先判断code值是否满足200，如果不是200，则提醒用户失败
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }

    /**
     * 通过用户id进行充值
     * @param mumber 实例对象
     * @return 实例对象
     */
    public Map<String,Object> recharge(Mumber mumber){
        this.mumberMapper.updateById(mumber);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg","充值成功!");

       return map;
    }

    /**
     * 查询当月/日所注册的人数
     * @return
     */
    public Map<String,Object> selectRegisterNumber(){
        List<RegisterNumber> registerNumber = mumberMapper.selectRegisterNumber();
        Map<String, Object> map = new HashMap<String, Object>();
        ArrayList<String> registerTime = new ArrayList<>();
        ArrayList<Integer> number = new ArrayList<>();
        for (RegisterNumber registers:registerNumber) {
            registerTime.add(registers.getRergistrationTime());
            number.add(registers.getId());
        }
        map.put("code", 200);
        map.put("msg","查询成功");
        map.put("registerTime",registerTime);
        map.put("number",number);
        return map;
    }
}
