package com.github.yinian.xxl.controller;

import javax.annotation.Resource;

import com.github.yinian.xxl.model.User;
import com.github.yinian.xxl.service.IDemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@Resource
	private IDemoService demoServiceHttp;

	@RequestMapping("/http")
	@ResponseBody
	public String http(String userName, String word) throws Exception {
		User user = demoServiceHttp.sayHi(new User(userName, word));
		return user.getWord().concat("-"+System.currentTimeMillis());
	}
	
	@Resource
	private IDemoService demoServiceNetty;
	
	@RequestMapping("/netty")
	@ResponseBody
	public String netty(String userName, String word) throws Exception {
		User user = demoServiceNetty.sayHi(new User(userName, word));
		return user.getWord().concat("-"+System.currentTimeMillis());
	}
}
