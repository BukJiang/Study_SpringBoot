package com.buk.designpattern.complex.two_way_adapter;

/**
 * 【双向适配器】
 * - 是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者
 *
 * @author jiangbk
 * @date 2021/4/16
 **/
public class TwoWayAdapter implements Target, Adaptee {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Adaptee adaptee = new ConcreteAdaptee();
        Target targetAdapter = new TwoWayAdapter(adaptee);
        targetAdapter.request("请求A");
        targetAdapter.request("请求B");

        Target target = new ConcreteTarget();
        Adaptee adapteeAdapter = new TwoWayAdapter(target);
        adapteeAdapter.specificRequest("具体请求A");
        adapteeAdapter.specificRequest("具体请求B");
    }

    /**
     * 【目标】
     */
    private Target target;

    /**
     * 【适配者】
     */
    private Adaptee adaptee;

    public TwoWayAdapter(Target target) {
        this.target = target;
    }

    public TwoWayAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * 请求接口
     *
     * @param param
     */
    @Override
    public void request(Object param) {
        param = "[双向适配器]-处理:{" + param + "}";
        adaptee.specificRequest(param);
    }

    /**
     * 具体请求
     *
     * @param param
     */
    @Override
    public void specificRequest(Object param) {
        param = "[双向适配器]-处理:{" + param + "}";
        target.request(param);
    }
}
