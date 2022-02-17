package com.geyy.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geyy.models.JwtTokenInfo;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author geyaowei
 * @title AuthFilterCustom
 * @description: TODO
 * @since 2022/2/17 10:21
 */
@Component
@Slf4j
public class AuthFilterCustom extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String base64Token =request.getHeader("token-info");
        if(StringUtils.isEmpty(base64Token)){
            log.info("未找到token信息");
            filterChain.doFilter(request,response);
            return;
        }
        byte[] decode = Base64.decode(base64Token);
        String tokenInfo = new String(decode, StandardCharsets.UTF_8);
        JwtTokenInfo jwtTokenInfo = objectMapper.readValue(tokenInfo, JwtTokenInfo.class);
        log.info("tokenInfo={}",objectMapper.writeValueAsString(jwtTokenInfo));
        List<String> authorities1 = jwtTokenInfo.getAuthorities();
        String[] authorities=new String[authorities1.size()];
        authorities1.toArray(authorities);
        //将用户信息和权限填充 到用户身份token对象中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(jwtTokenInfo.getUser_info()
                ,null
                , AuthorityUtils.createAuthorityList(authorities));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //将authenticationToken填充到安全上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}

