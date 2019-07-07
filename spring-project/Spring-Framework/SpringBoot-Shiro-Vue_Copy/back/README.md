# 详述

这部分主要是有关项目的后端部分。

## 异常处理

* 主要是结合`@ControllerAdvice`和`@ExceptionHandler`来进行统一异常处理

## shiro 安全校验 (config/shiro) 主要是back项目中使用到的

* FormAuthenticationFilter、FilterRegistrationBean、SecurityManager(shiro) 的使用

* AuthenticationTSecurityUtils.getSubject()oken类的使用

* LifecycleBeanPostProcessor的使用

* @DependsOn的使用

* AuthorizationAttributeSourceAdvisor的使用

* DefaultAdvisorAutoProxyCreator的使用
* WebMvcConfigurerAdapter的使用

* RequiresPermissions 的使用

## customize_annotations -- 不能运行
 1.主要是有关自定义注解的一些类
 2.实现多Realm配置: (multi_realm)
    这个不是从前端跳的，只是从postman测试，启动后端就好。由于有2个Shiroconfig:
    得把ShiroConfiguration.java中的`@configuration`去掉，只能运行ShiroMultiRealmConfig.java了
 3.如果和vue一起运行，只能去掉ShiroMultiRealmConfig.java中的`@configuration`了
 
