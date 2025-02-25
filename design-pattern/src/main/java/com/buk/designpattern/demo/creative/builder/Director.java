package com.buk.designpattern.demo.creative.builder;

/**
 * 【指挥者】
 * - 调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息
 * - 在应用过程中可以根据需要改变，如果创建的产品种类只有一种，只需要一个具体建造者，这时可以省略掉抽象建造者，甚至可以省略掉指挥者角色。
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public class Director {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }

    /**
     * 【抽象建造者】
     */
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构建与组装方法
     *
     * @return
     */
    public Product construct() {
        return builder.getResult();
    }
}
