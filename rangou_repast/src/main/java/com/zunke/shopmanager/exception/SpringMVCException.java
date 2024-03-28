package com.zunke.shopmanager.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 异常处理
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:20:02
 * @version 1.0
 */
@RestControllerAdvice
public class SpringMVCException {

    /**
     * 空指针异常
     */
    @ExceptionHandler(NumberFormatException.class)
    public Map<String, Object> numberFormatException(Throwable throwable){
        throwable.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", throwable.getMessage());
        return map;
    }

    /**
     * 找不到文件异常
     */
    @ExceptionHandler(FileNotFoundException.class)
    public Map<String, Object> fileNotFoundException(Throwable throwable){
        throwable.printStackTrace();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "对不起找不到文件异常");
        return map;
    }

    /**
     * 找不到类异常
     */
    @ExceptionHandler(ClassNotFoundException.class)
    public Map<String, Object> classNotFoundException(Throwable throwable) {
        throwable.printStackTrace();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "对不起！服务器驱动出错");
        return map;
    }

    /**
     * SQL异常
     */
    @ExceptionHandler(SQLException.class)
    public Map<String, Object> sqlException(Throwable throwable) {
        throwable.printStackTrace();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "对不起！数据库操作失败");
        return map;
    }

    /**
     * I/O异常
     */
    @ExceptionHandler(IOException.class)
    public Map<String, Object> ioException(Throwable throwable) {
        throwable.printStackTrace();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "对不起！文件上传失败");
        return map;
    }

    /**
     * 中断异常
     */
    @ExceptionHandler(InterruptedException.class)
    public Map<String, Object> interruptedException(Throwable throwable) {
        throwable.printStackTrace();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", "邮件发送失败");
        return map;
    }
}
