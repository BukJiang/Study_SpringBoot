package com.buk.designpattern.complex.two_way_adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体目标】
 * - 客户端需通过抽象目标访问所需的服务
 *
 * @author jiangbk
 * @date 2021/4/16
 **/
@Slf4j
public class ConcreteTarget implements Target {

    /**
     * 请求接口
     *
     * @param param
     */
    @Override
    public void request(Object param) {
        log.info("[目标]-收到请求:{{}}", param);
    }
}
