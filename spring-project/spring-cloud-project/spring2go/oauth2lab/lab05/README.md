参考
======

[Spring REST API + OAuth2 + AngularJS](http://www.baeldung.com/rest-api-spring-oauth2-angularjs)

[源码](https://github.com/Baeldung/spring-security-oauth)

### 实验步骤
1. 需要启动mysql数据库，在oauth-server/src/main/resources/persistence.properties设置数据库访问用户名/密码
2. 依次启动：
 * 授权服务器oauth-server(8081)
 * 资源服务器oauth-resource(8082)
 * AngularJS单页应用oauth-ui-implicit(0803) -- 页面有问题，无法使用
3. 浏览器打开http://localhost:8083 开始实验
4. 登录用户名密码在oauth-server/.../WebSecurityConfig中配置 

### 技术要点
1.使用了thymeleaf模板


==============================

## http://localhost:8083，打开后，无法展示，

oauth-ui-implicit 页面出现了问题，无法进行演示操作；但是功能完整。