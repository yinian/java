package com.imooc.kafkastudy;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 16:20 2019/4/24
 * @Modified By:
 */
public class MyProducer {

	private static KafkaProducer<String, String> producer;
	static {
		Properties properties = new Properties();
		//以逗号分隔的主机：端口对列表，用于建立与Kafka群集的初始连接
		properties.put("bootstrap.servers", "127.0.0.1:9092");
		//下面两个都是做序列化的
		properties.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		properties.put("partitioner.class",
				"com.imooc.kafkastudy.CustomPartitioner");

		producer = new KafkaProducer<String, String>(properties);
	}
	// 发送方式之一：只管发送，不管结果
	private static void sendMessageForgetResult() {

		/**
		 * RroducerRecord 构造方法里面的key ,value类型得和properties里面的key.serializer,value.serializer
		 * 的类型相同
		 * 在kafka中创建topic: bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic imooc-kafka-study
		 * 启动消费者：bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9002 --topic imooc-kafka-study --from-beginning
		 */
		ProducerRecord<String,String> record = new ProducerRecord<String, String>(
				"imooc-kafka-study", "name", "ForgetResult");
		producer.send(record);
		producer.close();
				;
	}
	// 同步发送消息
	private static void sendMessageSync() throws Exception {

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(
				"imooc-kafka-study", "name", "sync"
		);
		/**
		 * 发送失败异常包含两类：1.broker返回不可恢复的异常，那么生产者直接抛出这异常
		 * 2.对于broker其他异常，会进行重试，超过一定次数，仍然不能成功，则抛出异常；比如连接异常
		 */
		RecordMetadata result = producer.send(record).get();
		System.out.println(result.topic());
		System.out.println(result.partition());
		System.out.println(result.offset());
		producer.close();
	}
	// 异步发送
	private static void sendMessageCallback() {

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(
				"imooc-kafka-study-x", "name", "callback"
		);
		// 异步发送需要自己实现回调类
		producer.send(record,new MyProducerCallback());

		record = new ProducerRecord<String, String>(
				"imooc-kafka-study-x", "name-x","callback"
		);

		producer.send(record,new MyProducerCallback());

		record = new ProducerRecord<String, String>(
				"imooc-kafka-study-x", "name-y", "callback"
		);
		producer.send(record,new MyProducerCallback());

		record = new ProducerRecord<>(
				"imooc-kafka-study-x", "name-z", "callback"
		);
		producer.send(record, new MyProducerCallback());

		producer.close();




	}


	private static class MyProducerCallback implements Callback {

		@Override
		public void onCompletion(RecordMetadata recordMetadata, Exception e) {
			if (e != null){
				e.printStackTrace();
				return;
			}

			System.out.println(recordMetadata.topic());
			System.out.println(recordMetadata.partition());
			System.out.println(recordMetadata.offset());
			System.out.println("Coming in MyProducerCallback");
		}
	}

	public static void main(String[] args) throws Exception {

//		sendMessageForgetResult();
//		sendMessageSync();
		sendMessageCallback();
	}

}
