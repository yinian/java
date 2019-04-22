package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:49 2019/4/22
 * @Modified By:
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

	/**
	 * <h2>根据用户名查找用户记录</h2>
	 * */
	AdUser findByUsername(String username);
}
