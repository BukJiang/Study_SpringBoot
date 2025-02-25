package com.buk.designpattern.demo.structural.safe_composite;

/**
 * 【抽象构件】
 * - 为树叶构件和树枝构件声明公共接口，并实现它们的默认行为
 * - 在【透明式的组合模式】中抽象构件声明访问和管理子类的接口
 * - 在【安全式的组合模式】中不声明访问和管理子类的接口，管理工作由树枝构件完成
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public abstract class Component {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Composite compositeA = new Composite("安全A");
        //
        Leaf leafA1 = new Leaf("安全A1");
        Composite compositeAb = new Composite("安全AB");
        compositeA.add(leafA1);
        compositeA.add(compositeAb);
        //
        compositeAb.add(new Leaf("安全AB1"));
        compositeAb.add(new Leaf("安全AB2"));
        compositeAb.add(new Leaf("安全AB3"));
        //
        compositeA.operation();
    }

    /**
     * 节点名称
     */
    protected String name;

    /**
     * 操作
     */
    abstract void operation();
}
