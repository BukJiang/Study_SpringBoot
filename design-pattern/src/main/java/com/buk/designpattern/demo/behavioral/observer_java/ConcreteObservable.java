package com.buk.designpattern.demo.behavioral.observer_java;

import com.buk.designpattern.pojo.dto.DataDTO;

import java.util.Observable;

/**
 * 【具体可观察者】
 * - 也叫具体目标类
 * - 实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
public class ConcreteObservable extends Observable {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        ConcreteObserverA concreteObserverA = new ConcreteObserverA();
        ConcreteObserverB concreteObserverB = new ConcreteObserverB();
        Observable observable = new ConcreteObservable();
        observable.addObserver(concreteObserverA);
        observable.addObserver(concreteObserverB);
        observable.notifyObservers(DataDTO.builder().data("1").build());
        observable.notifyObservers(DataDTO.builder().data("2").build());
        observable.notifyObservers(DataDTO.builder().data("3").build());
    }

    @Override
    public void notifyObservers() {
        super.setChanged();
        super.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }
}
