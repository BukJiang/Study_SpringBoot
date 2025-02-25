package com.buk.interceptor.config;

import com.buk.interceptor.interceptor.MyAllowInterceptor;
import com.buk.interceptor.interceptor.MyAnnotationInterceptor;
import com.buk.interceptor.interceptor.MyRedirectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO: 添加拦截器配置
 *
 * @author BuK
 * @since 2020/08/19
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private MyAllowInterceptor myAllowInterceptor;

    @Autowired
    private MyRedirectInterceptor myRedirectInterceptor;

    @Autowired
    private MyAnnotationInterceptor myAnnotationInterceptor;

    /**
     * TODO: 添加拦截器
     * <p>
     * TODO: 路由：
     * - /**：匹配所有路径
     * - /example/**：匹配 /example/ 下的所有路径
     * - /example/*：匹配 /example/demo，不匹配 /example/demo/info
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 允许拦截器
        registry
                .addInterceptor(myAllowInterceptor)
        ;

        // 重定向拦截器
        registry
                .addInterceptor(myRedirectInterceptor)
                .addPathPatterns("/interceptor/redirect")
        ;

        // 注解拦截器
        registry
                .addInterceptor(myAnnotationInterceptor)
                .addPathPatterns("/annotation/interceptor");
    }
}
