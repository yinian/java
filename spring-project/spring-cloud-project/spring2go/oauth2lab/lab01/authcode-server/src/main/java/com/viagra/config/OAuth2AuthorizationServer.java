package com.viagra.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Author: HASEE
 * @Description: 授权服务配置
 * @Date: Created in 17:48 2019/5/9
 * @Modified By:
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends
				AuthorizationServerConfigurerAdapter{

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
				.withClient("clientapp")
				.secret("112233")
				.redirectUris("http://localhost:9001/callback")
				//授权码模式
				.authorizedGrantTypes("authorization_code")
				.scopes("read_userinfo","read_contacts");

	}
}
