package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.service.impl.ReportsDownloadExcelServiceImpl;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/14 17:04
 */
@Controller
@RequestMapping("/reports")
@Api(value = "报表中心excel下载管理",tags = "报表中心excel下载管理管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class ReportsDownloadExcelController {


    /**
     * 服务对象
     */
    @Resource
    private ReportsDownloadExcelServiceImpl reportsDownloadExcelServiceImpl;

    /**
     * @Author Yiming
     * @Description 流水表excel下载
     * @Date 9:15 2021/9/15
     * @Param [response]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping(value = "/excelOfAccount", method = RequestMethod.GET)
    @ApiOperation(value = "流水表excel下载接口",notes = "流水表excel下载接口",httpMethod = "GET")
    public void excelOfAccount(@RequestParam String ids, HttpServletResponse response) throws IOException {
        reportsDownloadExcelServiceImpl.excelOfAccount(ids, response);
    }
}
//http://localhost:8080/reports/excelOfAccount?ids=1,2,3