package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.service.impl.DownloadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/download")
@Api(value = "下载文件管理", tags = "下载文件管理接口API")
@ApiResponses({
        @ApiResponse(code = 400, message = "请求参数不完整或者错误"),
        @ApiResponse(code = 404, message = "找不到页面"),
        @ApiResponse(code = 405, message = "请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code = 406, message = "页面地址和接口名冲突"),
        @ApiResponse(code = 415, message = "请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code = 500, message = "后台服务逻辑错误")
})
public class DownloadController {

@Resource
private DownloadServiceImpl downloadServiceImpl;

    @GetMapping("/dishesPhoto")
    public String downloadTemplate(@RequestParam("photo") String photo){
        return "redirect:" + photo;
    }

    @GetMapping("/dishesTemplate")
    public void downloadDishesTemplate(String filePath, HttpServletResponse response,  HttpServletRequest request) throws IOException {
//        return "redirect:/download/template/dishesTemplate.xls";
        downloadServiceImpl.downloadDishesTemplate("/download/template/dishesTemplate.xls", "dishesTemplate.xls","GBK",response,request);
    }

    @GetMapping("/dishesExcel")
    public void downloadExcel(@RequestParam String id, HttpServletResponse response) throws IOException {
        downloadServiceImpl.downloadExcel(id, response);
    }


    /**
     * 下载模板
     *
     * @throws IOException
     */
    @GetMapping("/downloadTemplate")
    @ApiOperation(value = "下载模板接口",notes = "下载模板接口",httpMethod = "GET")

    public void downloadTemplate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        File file = new File("\\downloadTemplate\\dishesTemplate.xls");
//        File file = new File("E:\\idea_workspace\\teacther\\demo\\zunkeu8_project\\src\\main\\webapp\\download\\template\\dishesTemplate.xls");
        resp.setContentType("application/vnd.ms-excel; charset=UTF-8");

        resp.setHeader("Content-Disposition", "attachment; filename=dishesTemplate.xls");
        //  读取要下载的文件内容
        InputStream is = new FileInputStream(file);

        // 将输入流的数据传给输出流
        ServletOutputStream os = resp.getOutputStream();
        IOUtils.copy(is,os);

    }
}
