package com.buk.springsecurity.filter;

import com.buk.springsecurity.handler.MySecurityAuthenticationFailureHandler;
import com.buk.springsecurity.handler.MySecurityAuthenticationSuccessHandler;
import com.buk.springsecurity.manager.MySecurityAuthenticationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: 安全过滤器
 *
 * @author BuK
 * @since 2020/09/05
 */
@Slf4j
public class MySecurityAfterFilter extends AbstractAuthenticationProcessingFilter {

    public MySecurityAfterFilter(
            MySecurityAuthenticationManager mySecurityAuthenticationManager
    ) {
        super(new AntPathRequestMatcher("/**"));
        // 安全认证管理器
        this.setAuthenticationManager(mySecurityAuthenticationManager);
    }

    public MySecurityAfterFilter(
            MySecurityAuthenticationManager mySecurityAuthenticationManager,
            MySecurityAuthenticationSuccessHandler mySecurityAuthenticationSuccessHandler,
            MySecurityAuthenticationFailureHandler mySecurityAuthenticationFailureHandler
    ) {
        super(new AntPathRequestMatcher("/**"));
        // 安全认证管理器
        this.setAuthenticationManager(mySecurityAuthenticationManager);
        // 身份验证成功处理器
        this.setAuthenticationSuccessHandler(mySecurityAuthenticationSuccessHandler);
        // 身份验证失败处理器
        this.setAuthenticationFailureHandler(mySecurityAuthenticationFailureHandler);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.info("[安全过滤器:MySecurityAfterFilter] - doFilter");
        // 想往下走链路则 chain.doFilter(req, res)，否则直接return
        chain.doFilter(req, res);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("[安全过滤器:MySecurityAfterFilter] - attemptAuthentication");
        return null;
    }
}
