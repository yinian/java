package com.viagra.api;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:04 2019/5/10
 * @Modified By:
 */
public class UserInfo {

	private String name;

	private String email;

	public UserInfo(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
