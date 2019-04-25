package com.imooc.ad.index;

/**
 * @Author: HASEE
 * @Description: 索引的接口
 * @Date: Created in 11:27 2019/4/23
 * @Modified By:
 */
public interface IndexAware<K, V> {

	V get(K key);
	void add(K key, V value);
	void update(K key, V value);
	void delete(K key, V value);
}
