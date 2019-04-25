package com.imooc.kafkastudy;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 5:58 2019/4/25
 * @Modified By:
 */
public class MyConsumer {

	private static KafkaConsumer<String, String> consumer;
	private static Properties properties;

	static {

		properties = new Properties();

		properties.put("bootstrap.servers", "127.0.0.1:9092");
		properties.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id", "KafkaStudy");
	}
	// 自动提交位移
	private static void generalConsumeMessageAutoCommit() {

		properties.put("enable.auto.commit", true);
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Collections.singleton("imooc-kafka-study-x"));

		try {
			while (true) {// 无限循环去消费

				boolean flag = true;
				// poll ：没有数据时，等待多长时间
				ConsumerRecords<String,String> records = consumer.poll(100);

				for (ConsumerRecord<String,String> record : records){
					System.out.println(String.format(
							"topic = %s, partition = %s, key = %s, value = %s",
							record.topic(), record.partition(),
							record.key(), record.value()
					));
					if (record.value().equals("done")){
						flag= false;
					}
					if(!flag){
						break;
					}

				}

			}
		}finally {
			consumer.close();
		}


	}
// 手动提交位移
	private static void generalConsumeMessageSyncCommit() {

		properties.put("auto.commit.offset", false);
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));
		while (true) {
			boolean flag = true;
			ConsumerRecords<String, String> records =
					consumer.poll(100);
			for (ConsumerRecord<String,String> record : records){
				System.out.println(String.format(
						"topic = %s, partition = %s, key = %s, value = %s",
						record.topic(), record.partition(),
						record.key(), record.value()
				));
				if (record.value().equals("done")) {
					flag = false;
				}
			}

			try {
				// 使用手动提交，发生时方法会阻塞。
				// 相比异步提交，有重试机制
				consumer.commitAsync();
			}catch (CommitFailedException ex) {
				System.out.println("commit failed error: "
						+ ex.getMessage());
			}

			if (!flag) {
				break;
			}
		}
	}

	// 异步提交消息
	private static void generalConsumeMessageAsyncCommit() {

		properties.put("auto.commit.offset", false);
		consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));

		while (true) {
			boolean flag = true;

			ConsumerRecords<String, String> records =
					consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println(String.format(
						"topic = %s, partition = %s, key = %s, value = %s",
						record.topic(), record.partition(),
						record.key(), record.value()
				));
				if (record.value().equals("done")) {
					flag = false;
				}
			}
			// commit A, offset 2000
			// commit B, offset 3000
			// 相比同步提交，没有重试机制
			consumer.commitAsync();
			if (!flag) {
				break;
			}
		}
	}
	// 异步提交回调
	private static void generalConsumeMessageAsyncCommitWithCallback() {

		properties.put("auto.commit.offset", false);
		consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));

		while (true) {
			boolean flag = true;
			ConsumerRecords<String, String> records =
					consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println(String.format(
						"topic = %s, partition = %s, key = %s, value = %s",
						record.topic(), record.partition(),
						record.key(), record.value()
				));
				if (record.value().equals("done")) {
					flag = false;
				}
			}

			consumer.commitAsync((map,e)->{
				if (e != null) {
					System.out.println("commit failed for offsets: " +
							e.getMessage());
				}

			});
			if (!flag){
				break;
			}
		}
	}
	// 混合提交
	private static void mixSyncAndAsyncCommit() {

		properties.put("auto.commit.offset", false);
		consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Collections.singletonList("imooc-kafka-study-x"));

		try {

			while (true) {
				ConsumerRecords<String, String> records =
						consumer.poll(100);

				for (ConsumerRecord<String, String> record : records) {
					System.out.println(String.format(
							"topic = %s, partition = %s, key = %s, " +
									"value = %s",
							record.topic(), record.partition(),
							record.key(), record.value()
					));
				}
				consumer.commitAsync();
			}

		}catch (Exception ex) {
			System.out.println("commit async error: " + ex.getMessage());
		} finally {
			try {
				consumer.commitSync();
			}finally {
				consumer.close();
			}
		}

	}

	public static void main(String[] args) {
		generalConsumeMessageAutoCommit();
	}



}
