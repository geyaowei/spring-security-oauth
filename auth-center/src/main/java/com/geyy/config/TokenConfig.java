package com.geyy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author geyaowei
 * @title TokenConfig
 * @description: TODO
 * @since 2022/2/16 10:59
 */
@Configuration
public class TokenConfig {

    /*@Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }*/

    /**引入jwt**/
    private static final String SIGNING_KEY = "auth123";

    /**引入jwt**/
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**引入jwt**/
    /*@Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);//对称秘钥，资源服务器使用该秘钥来验证
        return jwtAccessTokenConverter;
    }*/


    @Autowired
    private UserDetailsService userDetailsService ;

    /**解决refresh-token接口缺少用户信息**/
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
        DefaultUserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
        userTokenConverter.setUserDetailsService(userDetailsService);
        tokenConverter.setUserTokenConverter(userTokenConverter);
        jwtAccessTokenConverter.setAccessTokenConverter(tokenConverter);
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);//对称秘钥，资源服务器使用该秘钥来验证
        return jwtAccessTokenConverter;
    }
}
