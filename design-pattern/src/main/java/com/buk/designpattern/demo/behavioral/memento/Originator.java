package com.buk.designpattern.demo.behavioral.memento;

/**
 * 【发起人】
 * - 记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，可以访问备忘录里的所有信息
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public class Originator {

    /**
     * 状态
     */
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 创建备忘录
     *
     * @return
     */
    public Memento createMemento() {
        return new Memento(state);
    }

    /**
     * 恢复备忘录
     *
     * @param memento
     */
    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }
}
