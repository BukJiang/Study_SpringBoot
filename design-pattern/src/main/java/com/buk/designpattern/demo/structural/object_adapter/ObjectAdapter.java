package com.buk.designpattern.demo.structural.object_adapter;

import com.buk.designpattern.demo.structural.class_adapter.Target;

/**
 * 【对象适配器】
 * - 是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者
 *
 * @author jiangbk
 * @date 2021/4/16
 **/
public class ObjectAdapter implements Target {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target objectAdapter = new ObjectAdapter(adaptee);
        objectAdapter.request("请求A");
        objectAdapter.request("请求B");
    }

    /**
     * 【适配者】
     */
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * 请求接口
     *
     * @param param
     */
    @Override
    public void request(Object param) {
        param = "[对象适配器]-处理:{" + param + "}";
        adaptee.specificRequest(param);
    }
}
