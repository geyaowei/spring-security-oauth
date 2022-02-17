package com.geyy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author geyaowei
 * @title TUser
 * @description: TODO
 * @since 2022/2/16 11:04
 */
@Data
@TableName("t_user")
public class TUser {

    private Integer id;

    private String username;

    private String password;

    private String fullname;

    private String mobile;
}
