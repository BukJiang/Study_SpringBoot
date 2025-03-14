package com.buk.springsecurity.pojo.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * TODO: 继承 org.springframework.security.core.userdetails.User
 *
 * @author BuK
 * @since 2020/09/05
 */
public class User extends org.springframework.security.core.userdetails.User {

    private String id;

    public User(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    public User(String id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
