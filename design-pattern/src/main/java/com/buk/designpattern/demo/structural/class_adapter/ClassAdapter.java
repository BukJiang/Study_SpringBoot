package com.buk.designpattern.demo.structural.class_adapter;

/**
 * 【类适配器】
 * - 是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者
 *
 * @author jiangbk
 * @date 2021/4/16
 **/
public class ClassAdapter extends Adaptee implements Target {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Target classAdapter = new ClassAdapter();
        classAdapter.request("请求A");
        classAdapter.request("请求B");
    }

    /**
     * 请求接口
     *
     * @param param
     */
    @Override
    public void request(Object param) {
        param = "[类适配器]-处理:{" + param + "}";
        specificRequest(param);
    }
}
