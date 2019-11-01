package com.yinian.rpc.sample.client.controller;

import com.yinian.rcp.remoting.invoker.annotation.XxlRpcReference;
import com.yinian.rpc.sample.api.DemoService;
import com.yinian.rpc.sample.api.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	
	@XxlRpcReference
	private DemoService demoService;


	@RequestMapping("")
	@ResponseBody
	public UserDTO http(String name) {

		try {
			return demoService.sayHi(name);
		} catch (Exception e) {
			e.printStackTrace();
			return new UserDTO(null, e.getMessage());
		}
	}

}
