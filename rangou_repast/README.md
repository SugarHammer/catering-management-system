# zunkeu8_plus

尊科u8 介绍
main

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  支付的使用说明<br/>
&nbsp;&nbsp;&nbsp;a.本工程测试的是账号支付方式(调用payOnline接口)，沙箱环境的账号支付有bug（存疑），
右上角的“完成”按钮时有时无。但是点击完成才会完成同步验签，所以出问题的时候建议多试几次，或者直接地址访问，如：
http://localhost/pay/payOnline?cartId=<br/>
&nbsp;&nbsp;&nbsp;b.在订单完成支付的时候，会有同步验签方式和异步回调验签方式，
异步回调需要做内网穿透，比较麻烦，所以订单完成的后续逻辑是与同步验签方式绑定的<br/>
&nbsp;&nbsp;&nbsp;c.测试的时候检查config/AlipayConfig下的notify_url和return_url的ip地址是localhost，如：
http://localhost:8080/return<br/>
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
