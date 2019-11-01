package com.yinian.rpc.sample.api;

import com.yinian.rpc.sample.api.dto.UserDTO;

/**
 * Demo API
 */
public interface DemoService {

	public UserDTO sayHi(String name);

}
