package com.imooc.ad.conf;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: HASEE
 * @Description: 消息转化器：实现java对象和HTTP请求之间的转换
 * @Date: Created in 16:47 2019/4/21
 * @Modified By:
 */
public class WebConfiguration implements WebMvcConfigurer{


	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>>
			                                           converters) {
		converters.clear();
		converters.add(new MappingJackson2HttpMessageConverter());

	}
}
