package com.imooc.ad.advice;

import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: HASEE
 * @Description: 统一响应拦截数据处理
 * @Date: Created in 15:34 2019/4/21
 * @Modified By:
 */
// 注解: 对响应实现统一拦截
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
	@Override
	public boolean supports(MethodParameter methodParameter,
	                        Class<? extends HttpMessageConverter<?>> aClass) {//是否支持拦截
		// 如果类或方法被声明为 IgnoreResponseAdvice，就不会被拦截
		if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class))
			return false;
		if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class))
			return false;
		return true;
	}
	// 将body包装成CommonResponse对象
	@Nullable
	@Override
	public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter,
	                              MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
	                              ServerHttpRequest serverHttpRequest,
	                              ServerHttpResponse serverHttpResponse) {//写入响应之前做一些操作

		CommonResponse<Object> response = new CommonResponse<>(0,"");
		if(null == o){
			return response;
		}else if(o instanceof CommonResponse){
			response = (CommonResponse<Object>) o;
		}else{
			response.setData(o);
		}

		return response;
	}
}
