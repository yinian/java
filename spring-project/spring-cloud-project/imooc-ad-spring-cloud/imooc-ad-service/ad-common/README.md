# 通用模块包括以下三部分
- 通用的代码定义、配置定义
- 统一的响应处理
- 统一的异常处理

## 响应处理的原理
![1](https://img-blog.csdnimg.cn/2019042023550784.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTQxNDgz,size_16,color_FFFFFF,t_70)
## 异常处理
![2](https://img-blog.csdnimg.cn/20190420235706339.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTQxNDgz,size_16,color_FFFFFF,t_70)

## 技术讲解
 - 使用了ResponseBodyAdvice 接口实现自定义返回数据类型，具体见`CommonResponseDataAdvice`类
