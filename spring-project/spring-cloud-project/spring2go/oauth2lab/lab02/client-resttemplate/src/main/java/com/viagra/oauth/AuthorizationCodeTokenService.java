package com.viagra.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthorizationCodeTokenService {


    @Autowired
    private AuthorizationCodeConfiguration configuration;

    public String getAuthorizationEndpoint() {
        // 4.拼装授权端点
        String endpoint = "http://localhost:8080/oauth/authorize";
        // 5.提供一些授权信息
        Map<String,String> authParameters = new HashMap<>();
        authParameters.put("client_id", "clientapp");
        authParameters.put("response_type", "code");
        authParameters.put("redirect_uri",
                getEncodedUrl("http://localhost:9001/callback"));
        authParameters.put("scope", getEncodedUrl("read_userinfo"));

        return buildUrl(endpoint,authParameters);


    }

    private String buildUrl(String endpoint, Map<String, String> parameters) {

        List<String> paramList = new ArrayList<>(parameters.size());

        parameters.forEach((name, value) -> {
            paramList.add(name + "=" + value);
        });

        return endpoint + "?" + paramList.stream()
              .reduce((a, b) -> a + "&" + b).get();
    }


        private String getEncodedUrl(String url) {
        try {
            /*
            URLDecoder 和 URLEncoder 用于完成普通字符串 和 application/x-www-form-urlencoded MIME 字符串之间的相互转换
             */
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public OAuth2Token getToken(String authorizationCode) {
        RestTemplate rest = new RestTemplate();
        /*
        拼装客户端需要认证的客户凭证
         */
        String authBase64 = configuration.encodeCredentials("clientapp",
                "112233");
        /*
        用RequestEntity 去构造一个请求
         */
        RequestEntity<MultiValueMap<String, String>> requestEntity = new RequestEntity<>(
            configuration.getBody(authorizationCode),
            configuration.getHeader(authBase64), HttpMethod.POST,
            URI.create("http://localhost:8080/oauth/token"));

        ResponseEntity<OAuth2Token> responseEntity = rest.exchange(
                requestEntity, OAuth2Token.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        throw new RuntimeException("error trying to retrieve access token");


    }


}
