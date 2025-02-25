package com.buk.designpattern.complex.two_way_adapter;

/**
 * 【抽象适配者】
 * - 是被访问及适配的现存组件库中组件接口
 *
 * @author jiangbk
 * @date 2021/4/16
 **/
public interface Adaptee {

    /**
     * 具体请求
     *
     * @param param
     */
    void specificRequest(Object param);
}
