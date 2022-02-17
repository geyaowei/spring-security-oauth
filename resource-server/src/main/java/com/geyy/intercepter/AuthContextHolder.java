package com.geyy.intercepter;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.geyy.dto.UserDetailsExpand;

/**
 * @author geyaowei
 * @title AuthContextHolder
 * @description: TODO
 * @since 2022/2/17 13:30
 */
public class AuthContextHolder {
    private TransmittableThreadLocal threadLocal = new TransmittableThreadLocal();
    private static final AuthContextHolder instance = new AuthContextHolder();

    private AuthContextHolder() {
    }

    public static AuthContextHolder getInstance() {
        return instance;
    }

    public void setContext(UserDetailsExpand t) {
        this.threadLocal.set(t);
    }

    public UserDetailsExpand getContext() {
        return (UserDetailsExpand)this.threadLocal.get();
    }

    public void clear() {
        this.threadLocal.remove();
    }
}

