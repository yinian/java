package com.viagra.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/*
fasterxml是一个SAX模式的XML解析器
@JsonProperty不仅仅是在序列化的时候有用，反序列化的时候也有用，具体例子参见测试用例。
 */
@Getter
@Setter
public class OAuth2Token {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;


}
