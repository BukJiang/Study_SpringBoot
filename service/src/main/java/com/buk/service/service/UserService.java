package com.buk.service.service;


import com.buk.service.pojo.entity.User;

/**
 * TODO: Service服务
 *
 * @author jiangbk
 * @date 2021/1/11
 **/
public interface UserService {

    /**
     * getUser
     *
     * @return
     */
    User getUser();

    /**
     * 默认方法
     */
    default void defaultMethod() {
        System.out.println("[UserService接口] defaultMethod");
    }
}
