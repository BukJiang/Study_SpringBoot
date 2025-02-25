package com.buk.springsecurity.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 安全
 *
 * @author BuK
 * @see com.buk.springsecurity.config.SecurityConfig Security配置
 * @since 2020/09/05
 */
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {

    // [需求]
    // 首页 - 无任何限制
    // 需要认证
    // 需要授权

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping({"", "/index"})
    public String index() {
        log.info("[安全] - 首页");
        return "[安全] - 首页";
    }

    /**
     * no
     *
     * @return
     */
    @RequestMapping({"/no"})
    public String no() {
        log.info("[安全] - 没有");
        return "[安全] - 没有";
    }

    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping({"/login"})
    public String login() {
        log.info("[安全] - 未登陆");
        return "[安全] - 未登陆";
    }

    /**
     * 登陆 - (PostMapping)
     *
     * @return
     */
    @PostMapping({"/toLogin"})
    public String toLogin(String username, String password) {
        String res = "[安全] - 登陆(username: " + username + ", password: " + password + ")";
        log.info(res);
        return res;

    }

    /**
     * 错误
     *
     * @return
     */
    @RequestMapping({"/error"})
    public String error() {
        log.info("[安全] - 错误");
        return "[安全] - 错误";
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping({"/logout"})
    public String logout() {
        log.info("[安全] - 注销");
        return "[安全] - 注销";
    }

    /**
     * 需要认证
     *
     * @return
     */
    @RequestMapping("/require/authentication")
    public String authentication() {
        log.info("[安全] - 已认证");
        return "[安全] - 已认证";
    }

    /**
     * 需要授权
     *
     * @param level
     * @return
     */
    @RequestMapping("/require/authorize/{level}")
    public String authorize(@PathVariable("level") Integer level) {
        log.info("[安全] - 已授权(level: " + level + ")");
        return "[安全] - 已授权(level: " + level + ")";
    }
}
