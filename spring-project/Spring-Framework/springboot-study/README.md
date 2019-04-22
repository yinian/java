# 主要是对 SpringBoot 的原理的学习
## 方面
 - 1.主要是对 `@Configuration` 的理解，具体实现在 CustomBeanConfig.java 中实现，
加深对`@SpringBootConfiguration` 的理解。
 - SpringBoot容器启动之后做一些自动配置，可以实现 `ApplicationRunner` 和
 `CommandLineRunner` 这两个类，具体参考 runner包。
 - `@ConfigurationProperties`：配置文件的信息，读取并自动封装成实体类。
具体参考MySQLConfig.java。
 - `@EnableScheduling`: 添加定时类。具体见schedule包。
 

