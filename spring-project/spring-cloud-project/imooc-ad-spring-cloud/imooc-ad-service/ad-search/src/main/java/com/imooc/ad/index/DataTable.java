package com.imooc.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: HASEE
 * @Description: Index 缓存
 * ApplicationContextAware 的作用：
 *  1.保存Spring上下文
 *  2.监听上下文启动，并完成相关操作
 *  PriorityOrdered是个接口，继承自Ordered接口，未定义任何方法。
 * 这段代码的逻辑：
 *　　1. 若对象o1是Ordered接口类型，o2是PriorityOrdered接口类型，那么o2的优先级高于o1
 *　　2. 若对象o1是PriorityOrdered接口类型，o2是Ordered接口类型，那么o1的优先级高于o2
 *　　3. 其他情况，若两者都是Ordered接口类型或两者都是PriorityOrdered接口类型，调用Ordered接口的getOrder方法得到order值，order值越大，优先级越小
 * 简单概括就是：
 * 　　OrderComparator比较器进行排序的时候，若2个对象中有一个对象实现了PriorityOrdered接口，那么这个对象的优先级更高。
 * 　　若2个对象都是PriorityOrdered或Ordered接口的实现类，那么比较Ordered接口的getOrder方法得到order值，值越低，优先级越高。
 * @Date: Created in 16:59 2019/4/23
 * @Modified By:
 */
@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered{

	private static ApplicationContext applicationContext;

	public static final Map<Class,Object> dataTableMap =
			new ConcurrentHashMap<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		DataTable.applicationContext = applicationContext;

	}

	@Override
	public int getOrder() {
		return PriorityOrdered.HIGHEST_PRECEDENCE;
	}

	public static <T> T of(Class<T> clazz) {
		T instance = (T)dataTableMap.get(clazz);
		if (null!=instance){
			return instance;
		}
		dataTableMap.put(clazz,bean(clazz));
		return (T) dataTableMap.get(clazz);
	}

	private static <T> T bean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}

	private static <T> T bean(Class clazz) {
		return (T) applicationContext.getBean(clazz);
	}
}
