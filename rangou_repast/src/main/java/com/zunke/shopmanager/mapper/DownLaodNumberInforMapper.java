package com.zunke.shopmanager.mapper;/**
 * @author ：EdgeYu
 * @date 2021-09-16 16:02
 * @version ：1.0
 */

import com.zunke.shopmanager.pojo.Mumber;

import java.util.List;

/**
 * @ClassName DownLaodNumberInfor
 * @Date 2021/9/16 16:02
 * @Author 何永波
 * @Description TODO
 */

public interface DownLaodNumberInforMapper {

    List<Mumber> selectByIds(List<String> ids);

}
