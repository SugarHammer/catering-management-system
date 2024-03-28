package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.service.impl.UploadServiceImpl;
import io.swagger.annotations.*;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/upload")
@Api(value = "上传文件管理", tags = "上传文件管理接口API")
@ApiResponses({
        @ApiResponse(code = 400, message = "请求参数不完整或者错误"),
        @ApiResponse(code = 404, message = "找不到页面"),
        @ApiResponse(code = 405, message = "请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code = 406, message = "页面地址和接口名冲突"),
        @ApiResponse(code = 415, message = "请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code = 500, message = "后台服务逻辑错误")
})
public class UploadController {
    @Resource
    private UploadServiceImpl uploadService;

    @PostMapping(value="/dishesPhoto",headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件接口",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses({
            @ApiResponse(code = 601,message = "上传文件发生异常")
    })
    public Map<String, Object> uploadPhoto(@ApiParam(value = "file",required = true) MultipartFile file, @ApiParam(value = "id" ,required = true) @RequestParam("id") String id, @ApiIgnore  HttpServletRequest request) throws SQLException, IOException, ClassNotFoundException, FileUploadException {
        return uploadService.uploadPhoto(file, id, request);
    }
    @PostMapping(value="/dishesComboPhoto",headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件接口",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses({
            @ApiResponse(code = 601,message = "上传文件发生异常")
    })
    public Map<String, Object> uploadComboPhoto(@ApiParam(value = "file",required = true) MultipartFile file, @ApiParam(value = "id" ,required = true) @RequestParam("id") String id, @ApiIgnore  HttpServletRequest request) throws SQLException, IOException, ClassNotFoundException, FileUploadException {
        return uploadService.uploadComboPhoto(file, id, request);
    }

    @PostMapping("/dishesExcel")
    public Map<String, Object> uploadExcel(MultipartFile file, HttpServletRequest request) throws SQLException, IOException, ClassNotFoundException, FileUploadException, IllegalAccessException {
        return uploadService.uploadExcel(file, request);
    }

}
