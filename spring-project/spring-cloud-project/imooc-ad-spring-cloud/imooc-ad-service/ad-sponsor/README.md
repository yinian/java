# ad-sponsor
这个模块就是广告投放系统。
主要涉及到了spring cloud中的feign、ribbon、hystrix组件。
## 常用注解
 - `@EnableFeignClients` 其他微服务可以调用该服务，或者可以被监控
 - `@EnableCircuitBreaker` 为了实现监控
 - `@EnableEurekaClient` 表示Eureka client端，用来拿到其他微服务的信息

