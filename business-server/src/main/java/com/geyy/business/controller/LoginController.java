package com.geyy.business.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geyy.business.model.LoginReq;
import com.geyy.business.model.WrapperResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author geyaowei
 * @title LoginController
 * @description: TODO
 * @since 2022/2/17 14:44
 */
@RestController
@Slf4j
public class LoginController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public WrapperResult<String> login(@RequestBody LoginReq loginReq) throws JsonProcessingException {
        log.info("收到登陆请求:{}", objectMapper.writeValueAsString(loginReq));
        String reqUrl = "http://auth-center/oauth/token?client_id=c1&client_secret=secret&grant_type=password&username=" + loginReq.getUsername() + "&password=" + loginReq.getPassword();
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(reqUrl, null, String.class);
        log.info(stringResponseEntity.getBody());
        return WrapperResult.success(stringResponseEntity.getBody());
    }
}

