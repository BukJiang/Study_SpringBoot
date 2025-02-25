package com.buk.designpattern.demo.creative.prototype;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体原型类】
 * - 实现抽象原型类的 clone() 方法，它是可被复制的对象
 *
 * @author jiangbk
 * @date 2021/4/22
 **/
@Slf4j
public class RealizeType implements Cloneable {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType obj1 = new RealizeType();
        RealizeType obj2 = (RealizeType) obj1.clone();
        log.info("obj1 == obj2 ? {}", obj1 == obj2);
    }

    /**
     * 克隆
     *
     * @return
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        log.info("[具体原型类]克隆");
        return super.clone();
    }
}
