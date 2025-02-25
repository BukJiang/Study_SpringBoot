package com.buk.designpattern.demo.structural.object_adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 【适配者】
 * - 是被访问及适配的现存组件库中组件接口
 *
 * @author jiangbk
 * @date 2021/4/16
 **/
@Slf4j
public class Adaptee {

    /**
     * 具体请求
     *
     * @param param
     */
    public void specificRequest(Object param) {
        log.info("[适配者]-收到具体请求:{{}}", param);
    }
}
