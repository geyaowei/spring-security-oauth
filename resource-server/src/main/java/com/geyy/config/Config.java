package com.geyy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author geyaowei
 * @title Config
 * @description: TODO
 * @since 2022/2/16 16:53
 */
@Configuration
@Import({com.geyy.config.ApiWebMvcConfig.class})
public class Config {
}

