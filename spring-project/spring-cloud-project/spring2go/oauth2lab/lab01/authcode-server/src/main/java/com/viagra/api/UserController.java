package com.viagra.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 17:47 2019/5/9
 * @Modified By:
 */
@Controller
public class UserController {

	// 资源API
	@RequestMapping("/api/userinfo")
	public ResponseEntity<UserInfo> getUserInfo(){
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String email = user.getUsername() + "@spring2go.com";
		UserInfo userInfo = new UserInfo();
		userInfo.setName(user.getUsername());
		userInfo.setEmail(email);

		return ResponseEntity.ok(userInfo);
	}


}
