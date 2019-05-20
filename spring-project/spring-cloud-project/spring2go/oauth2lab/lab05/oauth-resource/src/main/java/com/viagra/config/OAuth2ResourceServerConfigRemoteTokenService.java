package com.viagra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/*
资源服务器通过 @EnableResourceServer 注解来开启一个 OAuth2AuthenticationProcessingFilter 类型的过滤器
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfigRemoteTokenService extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests().anyRequest().permitAll();
    }

/*
@Primary可以理解为默认优先选择,同时不可以同时设置多个
RemoteTokenServices： 它将允许资源服务器通过HTTP请求来解码令牌（也就是授权服务的 /oauth/check_token 端点）。
如果你的资源服务没有太大的访问量的话，那么使用RemoteTokenServices
将会很方便（所有受保护的资源请求都将请求一次授权服务用以检验token值），
或者你可以通过缓存来保存每一个token验证的结果。
 */
    @Primary
    @Bean
    public RemoteTokenServices tokenServices(){

        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
        tokenService.setClientId("fooClientIdPassword");
        tokenService.setClientSecret("secret");
        return tokenService;

    }
}
