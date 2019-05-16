package com.viagra.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
fasterxml的序列化和反序列化
 */
public class OAuth2TokenTest {

    private
    static ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void serializeDemo() throws JsonProcessingException {
        OAuth2Token oAuth2Token = new OAuth2Token();

         oAuth2Token.setAccessToken("20000ms");

        System.out.println(objectMapper.writeValueAsString(oAuth2Token));
    }

    @Test
    public void deserializeDemo() throws IOException {

        String data = "{\"access_token\":\"20000ms\",\"token_type\":null,\"expires_in\":null,\"refresh_token\":null}";

        //从json映射到java对象，得到country对象后就可以遍历查找
        OAuth2Token token = objectMapper.readValue(data, OAuth2Token.class);
        System.out.println("access_token:"+token.getAccessToken());



    }

    @Test
    public void streamDemo(){

        Map<String, String> parameters = new HashMap<String,String>();
        parameters.put("key1","val1");
        parameters.put("key2","val2");
        parameters.put("key3","val3");

            List<String> paramList = new ArrayList<>();
        parameters.forEach((name, value) -> {
            paramList.add(name + "=" + value);
        });


        System.out.println(paramList.stream()
                .reduce((a,b)->a + "&" + b).get());

    }
}
