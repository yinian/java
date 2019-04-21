package com.imooc.ad.advice;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: HASEE
 * @Description: 异常拦截
 * @Date: Created in 16:33 2019/4/21
 * @Modified By:
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(value = AdException.class)
	public CommonResponse<String> handlerAdException(HttpServletRequest req,
	                                                 AdException ex){
		CommonResponse<String> response = new CommonResponse<>(-1,
				"business error");
		response.setData(ex.getMessage());
		return response;





	}
}
