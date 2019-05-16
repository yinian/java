package com.viagra.user;


import java.net.URI;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.viagra.oauth.AuthorizationCodeTokenService;
import com.viagra.oauth.OAuth2Token;
import com.viagra.security.ClientUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPage {

    @Autowired
    private UserRepository users;

    @Autowired
    private AuthorizationCodeTokenService tokenService;

    @GetMapping("/")
    public String home() {
        return "index";
    }
/*
这个方法是给授权服务器回调用的
 */
    @GetMapping("/callback")
    public ModelAndView callback(String code, String state) {

        ClientUserDetails userDetails = (ClientUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        ClientUser clientUser = userDetails.getClientUser();
        /*
        去授权服务器拿取token
         */
        OAuth2Token token = tokenService.getToken(code);
        clientUser.setAccessToken(token.getAccessToken());

        Calendar tokenValidity = Calendar.getInstance();
        long validIn = System.currentTimeMillis() + Long.parseLong(token.getExpiresIn());
        tokenValidity.setTime(new Date(validIn));
        clientUser.setAccessTokenValidity(tokenValidity);
        // 保存token
        users.save(clientUser);

        return new ModelAndView("redirect:/mainpage");


    }

    /*
    mainpage.html：获取服务器上的一些配置信息
     */
    @GetMapping("/mainpage")
    public ModelAndView mainpage() {
        /*
        获取令牌
         */
        ClientUserDetails userDetails = (ClientUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        ClientUser clientUser = userDetails.getClientUser();
        /*
        callback()走完，第二次进来，clientUser.getAccessToken() !== null
         */
        //1.先看数据库有无令牌
        if (clientUser.getAccessToken() == null) {// 2.没有
            // 3.授权，获取令牌
            String authEndpoint = tokenService.getAuthorizationEndpoint();
            // 6. 重定向到授权服务器，去拿token
            return new ModelAndView("redirect:" + authEndpoint);
        }

        clientUser.setEntries(Arrays.asList(
                new Entry("entry 1"),
                new Entry("entry 2")));

        ModelAndView mv = new ModelAndView("mainpage");

        mv.addObject("user", clientUser);
        // 正式向资源服务器发起请求，获取资源信息
        tryToGetUserInfo(mv, clientUser.getAccessToken());
        // 返回clientUser，放在页面展示
        return mv;
    }

    private void tryToGetUserInfo(ModelAndView mv, String token) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        // 验证token
        headers.add("Authorization", "Bearer " + token);
        // 服务端点
        String endpoint = "http://localhost:8080/api/userinfo";

        try {
            // 正确的话能给一个UserInfo的响应
            RequestEntity<Object> request = new RequestEntity<>(
                headers, HttpMethod.GET, URI.create(endpoint));

            ResponseEntity<UserInfo> userInfo = restTemplate.exchange(request, UserInfo.class);

            if (userInfo.getStatusCode().is2xxSuccessful()) {
                mv.addObject("userInfo", userInfo.getBody());
            } else {
                throw new RuntimeException("it was not possible to retrieve user profile");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("it was not possible to retrieve user profile");
        }
    }


}
