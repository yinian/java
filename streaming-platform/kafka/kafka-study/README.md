### kafka的学习

主要包括以下几部分
- 使用原生API发送消息
- 自定义分区分配器
- 使用原生API消费消息

###　kafka例子
在windows环境执行的，使用的是kafka自带的zookeeper。
zookeeper启动：
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
kafka启动：
.\bin\windows\kafka-server-start.bat .\config\server.properties
创建topic:
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic imooc-kafka-study
启动consumer:
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic imooc-kafka-study --from-beginning

