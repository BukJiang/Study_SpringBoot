package com.buk.springsecurity.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO: 身份验证成功处理器
 *
 * @author BuK
 * @since 2020/09/05
 */
@Slf4j
@Component
public class MySecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取 /用户名
        String userName = (String) authentication.getPrincipal();

        log.info("[身份验证成功处理器] - userName: {}", userName);
        // 响应
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write("[身份验证成功处理器] - userName: " + userName);
    }
}
