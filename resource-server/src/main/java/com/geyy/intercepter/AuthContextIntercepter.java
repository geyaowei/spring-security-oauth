package com.geyy.intercepter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geyy.dto.UserDetailsExpand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author geyaowei
 * @title AuthContextIntercepter
 * @description: TODO
 * @since 2022/2/17 13:47
 */
@Slf4j
@Component
public class AuthContextIntercepter implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("access AuthContextIntercepter ......");
        if(Objects.isNull(authentication) || Objects.isNull(authentication.getPrincipal())){
            //无上下文信息，直接放行
            return true;
        }
        UserDetailsExpand principal = (UserDetailsExpand) authentication.getPrincipal();
        AuthContextHolder.getInstance().setContext(principal);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextHolder.getInstance().clear();
    }
}

