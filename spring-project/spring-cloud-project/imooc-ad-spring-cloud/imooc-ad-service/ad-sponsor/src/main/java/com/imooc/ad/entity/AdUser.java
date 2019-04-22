package com.imooc.ad.entity;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 6:45 2019/4/22
 * @Modified By:
 */

import com.imooc.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Author: HASEE
 * @Description: 用户
 * @Date: Created in 6:39 2019/4/22
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_user")
public class AdUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Basic
	@Column(name = "username", nullable = false)
	private String username;

	@Basic
	@Column(name = "token", nullable = false)
	private String token;

	@Basic
	@Column(name = "user_status", nullable = false)
	private Integer userStatus;

	@Basic
	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Basic
	@Column(name = "update_time", nullable = false)
	private Date updateTime;

	public AdUser(String username, String token) {

		this.username = username;
		this.token = token;
		this.userStatus = CommonStatus.VALID.getStatus();
		this.createTime = new Date();
		this.updateTime = this.createTime;
	}
}

