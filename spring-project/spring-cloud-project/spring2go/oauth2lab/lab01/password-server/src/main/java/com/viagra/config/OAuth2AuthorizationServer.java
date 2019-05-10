package com.viagra.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @Author: HASEE
 * @Description: 授权服务器配置
 * @Date: Created in 13:53 2019/5/10
 * @Modified By:
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter{

	// 用户认证
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/* 把AuthenticationManager 绑定在endpoints
		  因为 密码模式：用户名/密码直接传递给授权服务器，然后交由AuthenticationManager来进行验证
		*/
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("clientapp")
				.secret("112233")
				// 密码模式
				.authorizedGrantTypes("password")
				.scopes("read_userinfo","read_contacts");
	}
}
