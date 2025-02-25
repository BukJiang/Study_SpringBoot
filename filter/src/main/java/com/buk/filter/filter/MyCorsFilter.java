package com.buk.filter.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * TODO: Cors过滤器
 *
 * @author BuK
 * @since 2020/08/19
 */
@Configuration
public class MyCorsFilter {

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        final CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        //多长时间内（单位s）无需在请求时发送预检请求，从而减少不必要的预检请求。
        config.setMaxAge(300L);
        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//          config.addExposedHeader("*");

        //2.添加映射路径
        final UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}
