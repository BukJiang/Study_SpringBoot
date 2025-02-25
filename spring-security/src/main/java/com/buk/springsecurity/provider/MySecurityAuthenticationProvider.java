package com.buk.springsecurity.provider;

import com.buk.springsecurity.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * TODO: 认证提供者
 *
 * @author BuK
 * @since 2020/09/05
 */
@Component
public class MySecurityAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // SpringSecurity 提供的加密工具，可快速实现加密加盐
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // 获取 /用户名 /密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        // 获取 用户信息
        User user = (User) userDetailsService.loadUserByUsername(userName);

        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("[认证提供者] - 密码错误！");
        }

        // 根据业务逻辑更新token
        String token = UUID.randomUUID().toString();
        // UsernamePasswordAuthenticationToken
        authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
