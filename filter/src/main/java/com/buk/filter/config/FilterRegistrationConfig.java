package com.buk.filter.config;

import com.buk.filter.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;

/**
 * TODO: 过滤器注册配置
 *
 * @author BuK
 * @since 2020/08/19
 */
@Configuration
public class FilterRegistrationConfig {

    /**
     * 过滤器注册
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<Filter> myFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        // 添加过滤器
        MyFilter myFilter = new MyFilter();
        filterFilterRegistrationBean.setName("myFilter");
        filterFilterRegistrationBean.setFilter(myFilter);
        filterFilterRegistrationBean.setOrder(-1);
        // 声明过滤路径
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("/*");
        filterFilterRegistrationBean.setUrlPatterns(urlList);
        //
        return filterFilterRegistrationBean;
    }
}
