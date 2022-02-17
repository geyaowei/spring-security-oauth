package com.geyy.dto;


import lombok.Data;

/**
 * @author geyaowei
 * @title UserDetailsExpand
 * @description: TODO
 * @since 2022/2/17 11:36
 */
@Data
public class UserDetailsExpand {

    private String username;

    //userId
    private Integer id;

    //电子邮箱
    private String email;

    //手机号
    private String mobile;

    private String fullname;
}

