package com.imooc.ad.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: HASEE
 * @Description: 定时任务类
 * @Date: Created in 22:57 2019/4/21
 * @Modified By:
 */
@Component
public class ScheduledTask {

	// @Scheduled(fixedRate = 5000)         上一次开始执行时间点之后5秒再执行
	// @Scheduled(fixedDelay = 5000)        上一次执行完毕时间点之后5秒再执行
	// @Scheduled(cron = "*/5 * * * * *")   通过 crontab 表达式定义规则

	@Scheduled(fixedRate = 1000)
	public void helloSpringBoot() {
		System.out.println("Hello SpringBoot");
	}
}
