package com.geyy.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author geyaowei
 * @title UserDetailsExpand
 * @description: TODO
 * @since 2022/2/17 11:42
 */
@Setter
@Getter
public class UserDetailsExpand extends User {

    public UserDetailsExpand(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    //userId
    private Integer id;

    private String username;

    //电子邮箱
    private String email;

    private String fullname;

    //手机号
    private String mobile;


}

