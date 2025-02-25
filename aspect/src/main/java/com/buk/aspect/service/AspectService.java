package com.buk.aspect.service;

import org.springframework.stereotype.Service;

/**
 * TODO: 切面服务
 *
 * @author BuK
 * @since 2020/08/19
 */
@Service
public class AspectService {

    public String execute(boolean isThrowEx) throws RuntimeException {
        if (isThrowEx) {
            throw new RuntimeException("[切面服务]: AspectService.execute->异常");
        }
        return "[切面服务]: AspectService.execute->返回";
    }
}
