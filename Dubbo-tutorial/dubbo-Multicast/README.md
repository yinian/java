## Dubbo 的 Multicast注册中心有下面特点：
   
* 需要启动任何中心节点，只要广播地址一样，就可以互相发现
* 组播受网络结构限制，只适合小规模应用或开发阶段使用。
* 组播地址段: 224.0.0.0 - 239.255.255.255

## 描述

这个例子主要是基于Dubbo-demo来进行的，也就是工程名dubbo-Multicast(dubbo-demo)来进行的。
1.启动Provider.java
2.启动Consumer.java

## 有关组播的概念
![](https://img-blog.csdnimg.cn/20190714174429508.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTQxNDgz,size_16,color_FFFFFF,t_70)

 使用WireShark可以查看具体的封包情况

