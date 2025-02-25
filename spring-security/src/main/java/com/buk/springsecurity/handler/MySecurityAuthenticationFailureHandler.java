package com.buk.springsecurity.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: 身份验证失败处理器
 *
 * @author BuK
 * @since 2020/09/05
 */
@Slf4j
@Component
public class MySecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            log.info("[身份验证失败处理器] - 用户名或密码错误！");
        } else if (exception instanceof DisabledException) {
            log.info("[身份验证失败处理器] - 账户被禁用！");
        } else if (exception instanceof AccountExpiredException) {
            log.info("[身份验证失败处理器] - 账户过期！");
        } else if (exception instanceof CredentialsExpiredException) {
            log.info("[身份验证失败处理器] - 凭证过期！");
        } else if (exception instanceof LockedException) {
            log.info("[身份验证失败处理器] - 账户被锁定！");
        } else {
            log.info("[身份验证失败处理器] - 其他错误！");
        }

        log.info("[身份验证失败处理器] - {}", exception);
        // 响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("[身份验证失败处理器] - 错误信息: " + exception.getMessage());
    }
}
