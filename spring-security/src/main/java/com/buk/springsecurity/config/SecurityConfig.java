package com.buk.springsecurity.config;

import com.buk.springsecurity.filter.MySecurityAfterFilter;
import com.buk.springsecurity.filter.MySecurityAtFilter;
import com.buk.springsecurity.filter.MySecurityBeforeFilter;
import com.buk.springsecurity.manager.MySecurityAuthenticationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TODO: Security配置
 *
 * @author BuK
 * @see WebSecurityConfigurerAdapter 自定义Security策略
 * @since 2020/09/05
 */
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private MySecurityAuthenticationManager mySecurityAuthenticationManager;

    /**
     * Http安全配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 禁用 csrf
                .csrf().disable()
                // 开启 cors
                .cors()
        ;

        http
                // 表单登陆
                .formLogin()
                // 登陆地址
                .loginPage("/security/login")
                // 登陆处理地址
                .loginProcessingUrl("/security/toLogin")
                // 成功地址
                .successForwardUrl("/security/index")
                // 错误地址
                .failureUrl("/security/error")

                // 自定义登陆用户名和密码属性名，默认为 username 和 password
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录成功处理器
//                .successHandler(
//                        (req, resp, authentication) -> {
//                            log.info("[Security配置] - 登录成功处理器");
//                        }
//                )
                // 登录失败处理器
                .failureHandler(
                        (req, resp, exception) -> {
                            log.info("[Security配置] - 登录失败处理器");
                        }
                )
                // 允许 以上使用到的地址
                .permitAll()
        ;

        http
                .sessionManagement()
        ;

        http
                // 注销
                .logout()
                // 注销地址
                .logoutUrl("/security/logout")
                // 注销成功处理器
                .logoutSuccessHandler(
                        (req, resp, exception) -> {
                            log.info("[Security配置] - 注销成功处理器");
                        }
                )
                // 允许 以上使用到的地址
                .permitAll()
        ;

        http
                .userDetailsService(userDetailsService)
        ;

        http
                // 授权请求
                .authorizeRequests()

                // antMatchers 路由匹配器 + permitAll 允许全部
                .antMatchers("/security/", "/security/index").permitAll()
                // antMatchers 路由匹配器 + authenticated 允许认证
                .antMatchers("/security/require/authentication").authenticated()
                // antMatchers 路由匹配器 + hasRole 需要角色{"1", "2", "3"}
                .antMatchers("/security/require/authorize/1").hasRole("1")
                .antMatchers("/security/require/authorize/2").hasRole("2")
                .antMatchers("/security/require/authorize/3").hasRole("3")

                // 其他
                // antMatchers 路由匹配器 + hasIpAddress 只允许127.0.0.1访问
                .antMatchers("/security/ip").hasIpAddress("127.0.0.1")
                // antMatchers 路由匹配器(匹配 OPTIONS 请求下的 /** 路径) + denyAll 拒绝全部
                .antMatchers(HttpMethod.OPTIONS, "/**").denyAll()

                // anyRequest 剩余的任何路由 + authenticated
                .anyRequest().authenticated()
        ;

        http
                // 自定义过滤器 在指定位置之前
                .addFilterBefore(new MySecurityBeforeFilter(mySecurityAuthenticationManager), UsernamePasswordAuthenticationFilter.class)
                // 自定义过滤器 在指定的同一个位置
                .addFilterAt(new MySecurityAtFilter(mySecurityAuthenticationManager), UsernamePasswordAuthenticationFilter.class)
                // 自定义过滤器 在指定位置之后
                .addFilterAfter(new MySecurityAfterFilter(mySecurityAuthenticationManager), UsernamePasswordAuthenticationFilter.class)
        ;
    }

}