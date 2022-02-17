package com.geyy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geyy.dto.UserDetailsExpand;
import com.geyy.intercepter.AuthContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geyaowei
 * @title OrderController
 * @description: TODO
 * @since 2022/2/16 14:22
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/r1")
    @PreAuthorize("hasAnyAuthority('r1')")//示请求者必须拥有r1权限，r1权限定义在auth-center服务表的t_permission表
    public UserDetailsExpand  r1(){
        //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //        Object principal = authentication.getPrincipal();
        //        return objectMapper.writeValueAsString(principal);

        UserDetailsExpand context = AuthContextHolder.getInstance().getContext();
        return context;
    }
}

