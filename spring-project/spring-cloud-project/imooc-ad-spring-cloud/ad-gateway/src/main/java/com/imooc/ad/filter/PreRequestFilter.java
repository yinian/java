package com.imooc.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @Author: HASEE
 * @Description: 日志打印
 * @Date: Created in 22:56 2019/4/20
 * @Modified By:
 */
@Slf4j
@Component //过滤器才能被发现，注册到容器中
public class PreRequestFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {// 值越小，越早被发现
		return 0;
	}

	@Override
	public boolean shouldFilter() {//需要过滤器永远执行
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.set("startTime",System.currentTimeMillis());//记下当前的时间戳，这个时间要传到下一个过滤器
		return null;
	}
}
