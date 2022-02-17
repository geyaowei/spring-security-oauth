package com.geyy.enhancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geyaowei
 * @title CustomTokenEnhancer
 * @description: TODO
 * @since 2022/2/17 11:10
 */
@Slf4j
@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String,Object> additionalInfo = new HashMap<>();
        Object principal = authentication.getPrincipal();//从OAuth2Authentication对象取出principal对象
        try {
            String s = objectMapper.writeValueAsString(principal);//转换principal对象为map
            Map map = objectMapper.readValue(s, Map.class);
            map.remove("password");
            map.remove("authorities");
            map.remove("accountNonExpired");
            map.remove("accountNonLocked");
            map.remove("credentialsNonExpired");
            map.remove("enabled");
            additionalInfo.put("user_info",map);
            ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);//将map对象填充进入OAuth2AccessToken对象的additionalInfo属
        } catch (IOException e) {
            log.error("",e);
        }

        return accessToken;
    }
}

