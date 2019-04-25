# 基于Spring Cloud微服务架构的广告系统

广告系统是能根据广告的请求进行匹配，并返回合适的广告创意数据，最终完成给用户的展示，以及曝光。
![1](https://img-blog.csdnimg.cn/20190420172014452.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTQxNDgz,size_16,color_FFFFFF,t_70)

# Architecture
主要包括广告投放系统和广告检索系统

## 使用了那些技术
- JDK1.8
- MySQL8.0
- kafka 2.1.0
- Spring Cloud Finchley
- Spring 5.0

建表语句在(imooc-ad-spring-cloud\imooc-ad-service\ad-sponsor\src\main\resources\ad-sponsor.sql) 目录下
<<<<<<< HEAD
初始化语句在(imooc-ad-spring-cloud\imooc-ad-service\ad-sponsor\src\main\resources\imooc-ad-init-data.sql)下
## 具体操作
1.通过执行ad-sponsor下的DumpDataService类的`dumpAdTableData`方法的测试用例，将数据表数据导出到文件中，用于将来

启动检索服务的时候，构建全量索引。导出目录在 ad-common/src/main/resources/mysql_data目录下。

2.编写测试用例：

主要是对业务模块进行测试 都在对应的test目录下，测试用例包括以下模块

    1) ad-sponsor：广告投放系统的测试 
    2)ad-search：广告检索系统的测试 
    
    1和2不用启动ad-eureka，只需开始执行对应test包下的测试用例
投放系统的HTTP请求测试：
    统一响应：返回CommonReponse对象
    统一异常：
    通过网关的一些信息。
    启动ad-eureka,启动ad-gateway,ad-sponsor
    
    

=======
>>>>>>> e51e3f4fe8253160f6942363c23c0c045e9253f2

## 详情
[详情](https://blog.csdn.net/m0_37941483/article/details/89420433)
