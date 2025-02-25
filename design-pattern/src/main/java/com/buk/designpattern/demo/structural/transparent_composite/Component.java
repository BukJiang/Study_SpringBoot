package com.buk.designpattern.demo.structural.transparent_composite;

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
        Composite compositeA = new Composite("透明A");
        //
        Leaf leafA1 = new Leaf("透明A1");
        Composite compositeAb = new Composite("透明AB");
        compositeA.add(leafA1);
        compositeA.add(compositeAb);
        //
        compositeAb.add(new Leaf("透明AB1"));
        compositeAb.add(new Leaf("透明AB2"));
        compositeAb.add(new Leaf("透明AB3"));
        //
        compositeA.operation();
    }

    /**
     * 节点名称
     */
    protected String name;

    /**
     * 添加
     *
     * @param c
     */
    abstract void add(Component c);

    /**
     * 移除
     *
     * @param c
     */
    abstract void remove(Component c);

    /**
     * 获取子节点
     *
     * @param i
     * @return
     */
    abstract Component getChild(int i);

    /**
     * 操作
     */
    abstract void operation();
}
