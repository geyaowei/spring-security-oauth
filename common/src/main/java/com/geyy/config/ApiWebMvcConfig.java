package com.geyy.config;

import com.geyy.AccessLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author geyaowei
 * @title ApiWebMvcConfig
 * @description: TODO
 * @since 2022/2/16 16:00
 */
@Configuration
@ComponentScan(basePackages = {"com.geyy"})
public class ApiWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AccessLogInterceptor accessLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLogInterceptor).addPathPatterns("/**");
    }
}

