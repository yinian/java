package com.imooc.kafkastudy;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * @Author: HASEE
 * @Description:  自定义消息分配器
 * @Date: Created in 22:45 2019/4/24
 * @Modified By:
 */
public class CustomPartitioner implements Partitioner{
	@Override
	public int partition(String topic,
	                     Object key, byte[] keyBytes,
	                     Object value, byte[] valueBytes,
	                     Cluster cluster) {

		List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
		int numPartitions = partitionInfos.size();

		if (null == keyBytes || !(key instanceof String)){
			throw new InvalidRecordException("kafka message must have key");
		}

		if (numPartitions == 1){// 第一个分区
			return 0;
		}
		if (key.equals("name")){//发送到最后一个分区
			return numPartitions - 1;
		}
		return Math.abs(Utils.murmur2(keyBytes) % (numPartitions - 1));// kafka默认分区策略

	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
}
