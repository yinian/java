# 广告检索系统

`@IgnoreResponseAdvice`: 屏蔽统一响应请求

client包：基于Feign实现微服务访问：声明式的web服务客户端，比ribbon访问更简单。
`AdPlan` 和`AdPlanGetRequest` 这两个是个演示案例，服务中具体用不到。
使用了`Hystrix` 断路器来防止雪崩。

index包：实现各种数据索引功能，具体详情请查看类。
        IndexFileLoader类：全量索引的具体实现
service包：BinlogServiceTest类：对mysql-binlog-connector-java 开源组件的测试用例。
mysql/dto包：按照resources/template.json 格式，对mysql-binlog-connector-java进行定制化开发.
sender包：增量索引
search包：广告检索

    1.DataTable: index缓存的实现
handler包：实现增量索引的一个Handler.





作用：通过Feign客户端调用微服务，通过Hystrix实现服务降级。

