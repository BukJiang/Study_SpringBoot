package com.buk.springsecurity.manager;

import com.buk.springsecurity.provider.MySecurityAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * TODO: 安全认证管理器
 *
 * @author BuK
 * @since 2020/09/05
 */
@Component
public class MySecurityAuthenticationManager implements AuthenticationManager {

    @Autowired
    private MySecurityAuthenticationProvider mySecurityAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticate = mySecurityAuthenticationProvider.authenticate(authentication);
        if (authenticate == null) {
            throw new ProviderNotFoundException("[安全认证管理器] - 认证提供者异常");
        }
        return authenticate;
    }
}
