package com.buk.designpattern.demo.behavioral.memento;

import lombok.extern.slf4j.Slf4j;

/**
 * 【管理者】
 * - 对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class Caretaker {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        //
        originator.setState("A");
        log.info("初始状态:{}", originator.getState());
        // 保存状态
        caretaker.setMemento(originator.createMemento());
        //
        originator.setState("B");
        log.info("更新状态:{}", originator.getState());
        // 恢复状态
        originator.restoreMemento(caretaker.getMemento());
    }

    /**
     * 【备忘录】
     */
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
