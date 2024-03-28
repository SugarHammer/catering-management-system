package com.zunke.shopmanager.controller;

/**
 * @author ：何
 * @date 2021-09-16 16:21
 * @version ：1.0
 */

import com.zunke.shopmanager.service.impl.DownLaodNumberInforServiceImpl;
import com.zunke.shopmanager.service.impl.UploadNumberInforServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @ClassName DownAndUploadController
 * @Date 2021/9/16 16:21
 * @Author 何永波
 * @Description TODO
 */
@Controller
@RequestMapping("/downAndUpload")
@Api(value = "上传下载(downAndUpload)管理", tags = "上传下载(downAndUpload)管理接口API")
@ApiResponses({
        @ApiResponse(code = 400, message = "请求参数不完整或者错误"),
        @ApiResponse(code = 404, message = "找不到页面"),
        @ApiResponse(code = 405, message = "请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code = 406, message = "页面地址和接口名冲突"),
        @ApiResponse(code = 415, message = "请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code = 500, message = "后台服务逻辑错误")
})
public class DownAndUploadController {
    @Resource
    private DownLaodNumberInforServiceImpl downLaodNumberInforService;
    @Resource
    private UploadNumberInforServiceImpl uploadNumberInforService;


    @RequestMapping(value = "/downloadMumberInfor", method = RequestMethod.GET)
    @ApiOperation(value = "下载会员接口", notes = "下载会员接口", httpMethod = "GET")
    public void downloadMumberInfor(@RequestParam String ids, HttpServletResponse response) throws IOException {

        System.out.println();
        downLaodNumberInforService.downloadNumberInfor(ids,response);

    }

 /*   @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    @ApiOperation(value = "下载会员信息模板接口", notes = "下载会员信息模板接口", httpMethod = "GET")
    public String downloadTemplate(@RequestParam HttpServletResponse response) throws IOException {

        //response.setCharacterEncoding("UTF-8");
        //response.setHeader("Content-Disposition", "attachment;filename="+System.currentTimeMillis()+".xls");
        //ServletOutputStream out = response.getOutputStream();


        return "redirect:/file/temp/mumberInformationTemplate.xlsx";
    }*/

    @RequestMapping(value = "/uploadMumberInfor", method = RequestMethod.POST)
    @ApiOperation(value = "批量上传会员信息接口", notes = "批量上传会员信息接口", httpMethod = "POST")
    @ResponseBody
    public Map<String,Object> uploadMumberInfor(MultipartFile file,HttpServletRequest request) throws IOException {

        System.out.println(file);

        return uploadNumberInforService.uploadMumberInfor(file,request,"upload");
    }

/*    @PostMapping(value="/dishesPhoto",headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件接口",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses({
            @ApiResponse(code = 601,message = "上传文件发生异常")
    })*/

    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    @ApiOperation(value = "下载会员信息模板接口", notes = "下载会员信息模板接口", httpMethod = "GET")
    public void downloadTemplate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        File file = new File("D:\\Java worker space\\zunke_u8\\src\\main\\webapp\\downloadTemplate\\mumberTemplate.xls");
        ServletContext context = req.getServletContext();
        resp.setContentType("application/vnd.ms-excel; charset=UTF-8");

        resp.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
        //  读取要下载的文件内容
        InputStream is = new FileInputStream(file);

        // 将输入流的数据传给输出流
        ServletOutputStream os = resp.getOutputStream();
        IOUtils.copy(is,os);

    }
}
