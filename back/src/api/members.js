export default {
    //会员基本信息表接口：
    //渲染会员基本信息接口
    membersInfo:'/api/mumber/selectForPage',
    //新增信息接口
    addinsert:"/api/mumber/insert",
    //删除信息接口
    del:'/api/mumber/deleteById',
    //编辑信息接口
    edit:'/api/mumber/updateById',
    //查询信息接口
    seaarch:'/api/mumber/selectForPage',
    //下载信息接口
    DownLoad:'/api/downAndUpload/downloadMumberInfor',



    //积分交易表接口
    //渲染积分交易信息接口
    integralTrading:'/api/integral/selectForPage',
    //查询信息接口
    seaarch1:'/api/integral/selectForPage',



    //会员充值表接口
    //渲染会员充值信息接口
    topUp:'/api/mumberRecharge/selectForPage',
    seaarch2:'/api/mumberRecharge/selectForPage',

    //批量新增下载会员模板接口
    DownLoadmodul: '/api/downAndUpload/downloadTemplate',
    //上传会员模板接口
    upload:'/api/downAndUpload/uploadMumberInfor'
}