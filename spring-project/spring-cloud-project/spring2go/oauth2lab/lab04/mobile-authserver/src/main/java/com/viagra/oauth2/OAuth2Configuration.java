package com.viagra.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
@Configuration
public class OAuth2Configuration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;
    // TokenGranter：令牌授予者
    public TokenGranter tokenGranter(){

        DefaultOAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(clientDetailsService());

        AuthorizationCodeServices codeServices = authorizationCodeServices();

        AuthorizationServerTokenServices tokenServices = tokenServices();
        List<TokenGranter> tokenGranters = Arrays.asList(
                new AuthorizationCodeTokenGranter(tokenServices,codeServices,clientDetailsService(),requestFactory),
                new ResourceOwnerPasswordTokenGranter(authenticationManager,tokenServices,clientDetailsService(),requestFactory),
                new ImplicitTokenGranter(tokenServices,clientDetailsService(),requestFactory));

        return new CompositeTokenGranter(tokenGranters);



    }
/*
Spring的@Bean注解用于告诉方法，产生一个Bean对象，
然后这个Bean对象交给Spring管理。
产生这个Bean对象的方法Spring只会调用一次，
随后这个Spring将会将这个Bean对象放在自己的IOC容器中。
 */
    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setAuthenticationManager(authenticationManager);
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }






}
