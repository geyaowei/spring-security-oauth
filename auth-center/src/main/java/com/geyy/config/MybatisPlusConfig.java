package com.geyy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.geyy.mapper")
public class MybatisPlusConfig {

}
