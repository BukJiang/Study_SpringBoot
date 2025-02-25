package com.buk.designpattern.demo.structural.transparent_composite;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * 【树枝构件】
 * - 组合中的分支节点对象，它有子节点，用于继承和实现抽象构件。它的主要作用是存储和管理子部件，通常包含 Add()、Remove()、GetChild() 等方法
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class Composite extends Component {

    /**
     * 子节点列表
     */
    private List<Component> children;

    public Composite(String name) {
        this.name = name;
        this.children = Lists.newArrayList();
    }

    /**
     * 添加
     *
     * @param c
     */
    @Override
    public void add(Component c) {
        children.add(c);
    }

    /**
     * 移除
     *
     * @param c
     */
    @Override
    public void remove(Component c) {
        children.remove(c);
    }

    /**
     * 获取子节点
     *
     * @param i
     * @return
     */
    @Override
    public Component getChild(int i) {
        return children.get(i);
    }

    /**
     * 操作
     */
    @Override
    public void operation() {
        log.info("[树枝构件]{}", name);
        Optional.ofNullable(children).orElse(Lists.newArrayList()).forEach(Component::operation);
    }
}
