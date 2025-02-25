package com.buk.filter.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * TODO: 过滤器
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
public class MyFilter implements Filter {

    /**
     * 过滤器初始化
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[过滤器]-初始化init");
    }

    /**
     * 处理过滤
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();

        // 过滤器链 后续处理
        filterChain.doFilter(servletRequest, servletResponse);

        long endTime = System.currentTimeMillis();
        // 执行时间
        long timeConsuming = endTime - startTime;
        log.info("[过滤器]-执行时间doFilter: {}ms", timeConsuming);
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        log.info("[过滤器]-销毁destroy");
    }
}
