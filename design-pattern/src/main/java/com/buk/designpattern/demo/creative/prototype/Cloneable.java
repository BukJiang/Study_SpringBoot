package com.buk.designpattern.demo.creative.prototype;

/**
 * 【抽象原型类】
 * - 具体原型对象必须实现的接口
 *
 * @author jiangbk
 * @date 2021/4/22
 **/
public interface Cloneable {

    /**
     * 克隆
     *
     * @return
     */
    Object clone() throws CloneNotSupportedException;
}
