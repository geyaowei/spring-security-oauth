package com.geyy.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.geyy.dto.UserDetailsExpand;
import com.geyy.entity.TUser;
import com.geyy.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author geyaowei
 * @title MyUserDetailsServiceImpl 接口通过userName获取用户密码信息用于校验用户密码登陆和权限信息
 * 实现UserDetailsService接口，不实现该接口，会出现后端死循环导致的stackoverflow问题。
 * @description: TODO
 * @since 2022/2/16 11:07
 */
@Service
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TUser tUser = userMapper.selectOne(new LambdaQueryWrapper<TUser>().eq(TUser::getUsername, username));
        if (Objects.isNull(tUser)) {
            throw new UsernameNotFoundException(username + "账号不存在");
        }
        List<String> allPermissions = userMapper.findAllPermissions(tUser.getId());
        String[] array = null;
        if (CollectionUtils.isEmpty(allPermissions)) {
            log.warn("{} 无任何权限", tUser.getUsername());
            array = new String[]{};
        } else {
            array = new String[allPermissions.size()];
            allPermissions.toArray(array);
        }
        /*return User
                .withUsername(tUser.getUsername())
                .password(tUser.getPassword())
                .authorities(array).build();*/
        UserDetailsExpand userDetailsExpand = new UserDetailsExpand(tUser.getUsername(), tUser.getPassword(), AuthorityUtils.createAuthorityList(array));
        userDetailsExpand.setId(tUser.getId());
        userDetailsExpand.setEmail("geyaowei@yeah.net");
        userDetailsExpand.setMobile(tUser.getMobile());
        userDetailsExpand.setFullname(tUser.getFullname());
        return userDetailsExpand;
    }
}
