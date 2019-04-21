# 通用模块包括以下三部分
- 通用的代码定义、配置定义
- 统一的响应处理
- 统一的异常处理

## 响应处理的原理
![1](https://img-blog.csdnimg.cn/2019042023550784.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTQxNDgz,size_16,color_FFFFFF,t_70)
## 异常处理
![2](https://img-blog.csdnimg.cn/20190420235706339.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTQxNDgz,size_16,color_FFFFFF,t_70)

## 技术讲解
 - 使用了`ResponseBodyAdvice` 接口实现自定义数据类型(Spring Boot)，具体见`CommonResponseDataAdvice`类，
 - 使用了`@ExceptionHandler` (Spring的异常处理)，具体见`GlobalExceptionAdvice`类，
 - 使用了`WebMvcConfigurer` (Spring的)实现消息转换，具体见`WebConfiguration`类
 
 | 类名 | 作用 | 默认值 |
| ------ | ------ | ------ |
| `CommonResponseDataAdvice` | 主要是完成了统一响应的处理 |  |
| `IgnoreResponseAdvice` |  主要是避免统一响应的拦截 |  |
| `GlobalExceptionAdvice` | 统一异常的拦截 |  |
| `WebConfiguration` | 完成消息转换 |  |
 
 
 
