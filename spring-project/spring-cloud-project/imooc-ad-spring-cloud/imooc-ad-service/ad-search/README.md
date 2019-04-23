# 广告检索系统

`@IgnoreResponseAdvice`: 屏蔽统一响应请求

基于Feign实现微服务访问：声明式的web服务客户端，比ribbon访问更简单。

`AdPlan` 和`AdPlanGetRequest` 这两个是个演示案例，服务中具体用不到。
使用了`Hystrix` 断路器来防止雪崩。

作用：通过Feign客户端调用微服务，通过Hystrix实现服务降级。
