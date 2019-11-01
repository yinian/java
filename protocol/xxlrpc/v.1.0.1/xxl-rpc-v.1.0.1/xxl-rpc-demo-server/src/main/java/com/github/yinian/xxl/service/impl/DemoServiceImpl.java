package com.github.yinian.xxl.service.impl;

import java.text.MessageFormat;

import com.github.yinian.xxl.model.User;
import com.github.yinian.xxl.rpc.netcom.netty.annotation.RpcService;
import com.github.yinian.xxl.service.IDemoService;
import org.springframework.stereotype.Service;

@RpcService(IDemoService.class)
@Service("demoService")
public class DemoServiceImpl implements IDemoService {
	
	@Override
	public User sayHi(User user) {
		return new User(user.getUserName(), MessageFormat.format("{0} say:{1}", user.getUserName(), user.getWord()));
	}

}
