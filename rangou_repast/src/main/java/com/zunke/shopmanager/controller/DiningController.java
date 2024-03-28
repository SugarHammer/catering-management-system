package com.zunke.shopmanager.controller;

import com.zunke.shopmanager.pojo.Dining;
import com.zunke.shopmanager.service.impl.DiningServiceImpl;
import com.zunke.shopmanager.service.impl.FileUpLoadServiceImpl;
import org.apache.commons.io.IOUtils;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
import java.sql.SQLException;
import java.util.Map;

/**
 * 餐桌表(Dining)表控制层
 *
 * @author ZhangHongNeng
 * @since 2021-09-13 11:20:01
 * @version 1.0
 */
@RestController
@RequestMapping("/dining")
@Api(value = "餐桌表(Dining)管理",tags = "餐桌表(Dining)管理接口API")
@ApiResponses({
        @ApiResponse(code=400,message="请求参数不完整或者错误"),
        @ApiResponse(code=404,message="找不到页面"),
        @ApiResponse(code=405,message="请求方式不正确，比如后台接收是post，但前端请求的是get"),
        @ApiResponse(code=406,message="页面地址和接口名冲突"),
        @ApiResponse(code=415,message="请求格式不正确，前端可能没有把请求头修改为json，也可能前端请求的json对象没有转换为字符串"),
        @ApiResponse(code=500,message="后台服务逻辑错误")
})
public class DiningController {

    /**
     * 服务对象
     */
    @Resource
    private DiningServiceImpl diningServiceImpl;
    @Resource
    private FileUpLoadServiceImpl fileUpLoadService;

    /**
     *
     * @param index         需要查询的页码
     * @param diningType    餐桌类型
     * @param diningNumber  餐桌可以坐的人数
     * @param diningAreaId  餐桌区域的主键
     * @param diningAction  餐桌状态
     * @return              返回所有结果
     */
    @RequestMapping(value = "/selectForPage", method = RequestMethod.GET)
    @ApiOperation(value = "列表查询分页接口",notes = "列表查询分页接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "name",value = "**名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "diningType",value = "餐桌类型",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "diningNumber",value = "餐桌可以坐的人数",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "diningAreaId",value = "餐桌区域的主键",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "diningAction",value = "餐桌状态",paramType = "query",dataType = "String"),
    })
    public Map<String, Object> selectForPage(@RequestParam(name = "page") int index, String diningType, String diningNumber,
                                             Integer diningAreaId, Integer diningAction) {
        return this.diningServiceImpl.selectForPage(index,diningType, diningNumber, diningAreaId, diningAction);
    }


    /**
     * 查询所有数据
     * @return 返回所有数据
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有数据接口",notes = "查询所有数据接口",httpMethod = "GET")
    public Map<String, Object> selectAll() {
        return this.diningServiceImpl.selectAll();
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "根据一个主键删除一条**记录的接口",notes = "根据一个主键删除一条**记录的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键",paramType = "query",dataType = "String",required = true),
    })
    public Map<String, Object> deleteById(@RequestParam("id") String id) {
        return this.diningServiceImpl.deleteById(id);
    }

    
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过主键查询一个**的接口",notes = "通过主键查询一个**的接口",httpMethod = "GET")
    public Map<String, Object> selectById(Integer id) {
        return this.diningServiceImpl.selectById(id);
    }



    /**
     * 通过主键更新单条数据
     *
     * @param dining 一个数据库对应的POJO数据对象
     * @return 单条数据
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ApiOperation(value = "通过主键更新一个**的接口",notes = "通过主键更新一个**的接口",httpMethod = "POST")
    public Map<String, Object> updateById(@RequestBody @ApiParam(name = "dining",value = "pojo模型",required = true) Dining dining) {
        return this.diningServiceImpl.updateById(dining);
    }
    
    /**
     * 通过一个pojo对象新增单条数据
     *
     * @param dining        一个数据库对应的POJO数据对象
     * @return              返回插入的主键id
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "根据完整记录新增一个用户的接口",notes = "根据完整记录新增一个用户的接口",httpMethod = "POST")
    public Map<String, Object> insert(@RequestBody @ApiParam(name = "dining",value = "pojo模型",required = true) Dining dining) {
        return this.diningServiceImpl.insert(dining);
    }


    /**
     * 通过Excel模板批量新增
     * @param file                          接收上传的文件对象
     * @param request                       请求对象
     * @return                              返回上传是否成功
     * @throws IOException                  文件上传异常
     */
    @PostMapping(value = "/insertAll",headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件接口",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses({
            @ApiResponse(code = 601,message = "上传文件发生异常")
    })
    public Map<String, Object> uploadExcel(@ApiParam(value = "file",required = true) MultipartFile file, HttpServletRequest request) throws IOException{
        System.out.println("file="+file);
        return fileUpLoadService.fileUpLoad(file, request, "uploadTemplate");
    }


    /**
     *
     * @param req
     * @param resp
     * @throws IOException  IO异常
     */
    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    @ApiOperation(value = "下载餐桌批量新增模板接口", notes = "下载餐桌批量新增模板接口", httpMethod = "GET")
    public void downloadTemplate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        File file = new File("E:\\idea_workspace\\tether\\zunke_u8\\src\\main\\webapp\\downloadTemplate\\diningTemplate.xls");
//        ServletContext context = req.getServletContext();
        resp.setContentType("application/vnd.ms-excel; charset=UTF-8");

        resp.setHeader("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".xls");
        //  读取要下载的文件内容
        InputStream is = new FileInputStream(file);

        // 将输入流的数据传给输出流
        ServletOutputStream os = resp.getOutputStream();
        IOUtils.copy(is,os);
    }

    /**
     * 通过餐桌状态码查询数据
     *
     * @param  diningAction 餐桌状态码
     * @return              实例对象
     */
    @RequestMapping(value = "/selectByDiningAction", method = RequestMethod.GET)
    @ApiOperation(value = "通过餐桌状态码查询的接口",notes = "通过餐桌状态码查询的接口",httpMethod = "GET")
    public Map<String, Object> selectByDiningAction(Integer diningAction) {
        return this.diningServiceImpl.selectByDiningAction(diningAction);
    }
}