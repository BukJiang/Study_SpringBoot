package com.buk.designpattern.demo.structural.safe_composite;

import lombok.extern.slf4j.Slf4j;

/**
 * 【树叶构件】
 * - 组合中的叶节点对象，它没有子节点，用于继承或实现抽象构件
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class Leaf extends Component {

    public Leaf(String name) {
        this.name = name;
    }

    /**
     * 添加
     *
     * @param c
     */
    public void add(Component c) {

    }

    /**
     * 移除
     *
     * @param c
     */
    public void remove(Component c) {

    }

    /**
     * 获取子节点
     *
     * @param i
     * @return
     */
    public Component getChild(int i) {
        return null;
    }

    /**
     * 操作
     */
    @Override
    public void operation() {
        log.info("[树叶构件]{}", name);
    }
}
