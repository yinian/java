package com.imooc.ad.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 13:10 2019/4/23
 * @Modified By:
 */
@Slf4j
public class CommonUtils {

	private static String[] parsePatterns = {
			"yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
	};

	/**
	 * 表示如果map中的key不存在，则通过factory.get() 返回一个新对象。
	 * @param key
	 * @param map
	 * @param factory
	 * @param <K>
	 * @param <V>
	 * @return
	 */
	public static <K, V> V getorCreate(K key, Map<K, V> map,
	                                 Supplier<V> factory){
		return map.computeIfAbsent(key, k -> factory.get());
	}

	public static String stringConcat(String... args) {
		StringBuilder result = new StringBuilder();
		for(String arg : args){
			result.append(arg);
			result.append("-");
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();

	}
	// Tue Jan 01 08:00:00 CST 2019
	public static Date parseStringDate(String dateString) {

		try {

			DateFormat dateFormat = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss zzz yyyy",
					Locale.US
			);
			return DateUtils.addHours(
					dateFormat.parse(dateString),
					-8
			);

		} catch (ParseException ex) {
			log.error("parseStringDate error: {}", dateString);
			return null;
		}
	}


}
