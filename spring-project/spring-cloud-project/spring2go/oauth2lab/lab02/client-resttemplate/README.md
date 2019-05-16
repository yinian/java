客户端以授权码方式访问OAuth2服务器案例，使用rest template
======
这是一个web应用程序，通过RestTemplate获取访问Token，
参考代码地址：

https://github.com/spring2go/oauth2lab/blob/master/lab02
## 目录结构
java/com/viagra包下，没用spring-security-oauth2-client客户端去访问，而是采用RestTemplate去简单的实现客户端访问OAuth2服务

|-- security                           
|   |-- SecurityConfiguration.java    // 做了一些拦截，ClientUserDetails.java，ClientUserDetailsService.java 帮助该类辅助实现。 
	|-- ClientUserDetails.java
	|-- ClientUserDetailsService.java  
|-- user                            
|   |-- MainPage.java                   // 核心配置器


test包下：是一些基础知识点的使用

## 运行方式，

1.得首先把lab01/authcode-server 授权码服务器启动起来，

2.然后再把client-resttemplate启动起来

访问地址:http://localhost:9001/


# 实验注意事项

1. 该实验开始前需要预先安装mysql数据库，再使用mysql workbench预先执行sql脚本src\main\resources\db\oauth_client.sql
2. 该案例需先启动lab01中的支持授权码模式的OAuth2服务器，端口在8080。
3. 再运行本案例web应用，端口在9001，浏览器访问http://locahost:9001， 按提示操作即可。
4. 该案例授权成功后，客户端会在数据库中缓存访问令牌（可通过mysql workbench查看），如果OAuth2服务器使用内存模式，则重启OAuth2服务器后原访问令牌将失效，需要清除数据库令牌再重新授权。

