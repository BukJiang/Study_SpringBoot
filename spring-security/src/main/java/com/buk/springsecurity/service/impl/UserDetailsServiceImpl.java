package com.buk.springsecurity.service.impl;

import com.buk.springsecurity.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * TODO: UserDetailsService 实现类
 *
 * @author BuK
 * @since 2020/09/05
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 示例
        // 根据 username 读取数据库信息
        // 如果不存在
        // if (!存在) {
        //     throw new UsernameNotFoundException("用户不存在！");
        // }
        // return 用户信息;

        // SpringSecurity 提供的加密工具，可快速实现加密加盐
        // BCrypt密码编码器
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        // 密码要加 "{类型}"
        String password = "{bcrypt}" + bCryptPasswordEncoder.encode("123456");
        log.info("[UserDetailsService 实现类] - username: {}, password: {}", username, password);
        // 角色前缀 "ROLE_"
        // 自定义用户信息，继承 org.springframework.security.core.userdetails.User
        return new User(
                "id",
                // 用户名
                username,
                // 密码
                password,
                // 是否允许
                true,
                // 账户没有过期
                true,
                // 凭证没有过期
                true,
                // 账户没有被锁定
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_1, ROLE_2, ROLE_3")
        );
    }
}
