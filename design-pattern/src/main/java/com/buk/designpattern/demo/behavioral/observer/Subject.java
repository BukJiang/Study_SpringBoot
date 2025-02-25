package com.buk.designpattern.demo.behavioral.observer;

import com.buk.designpattern.pojo.dto.DataDTO;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 【抽象主题】
 * - 也叫抽象目标类
 * - 提供了保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
abstract class Subject {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Observer concreteObserverA = new ConcreteObserverA();
        Observer concreteObserverB = new ConcreteObserverB();
        Subject subject = new ConcreteSubject();
        subject.addObserver(concreteObserverA);
        subject.notifyObservers(DataDTO.builder().data("1").build());
        subject.addObserver(concreteObserverB);
        subject.notifyObservers(DataDTO.builder().data("2").build());
        subject.removeObserver(concreteObserverA);
        subject.notifyObservers(DataDTO.builder().data("3").build());
        subject.removeObserver(concreteObserverB);
        subject.notifyObservers(DataDTO.builder().data("4").build());
    }

    /**
     * 观察者列表
     */
    protected List<Observer> observerList;

    protected Subject() {
        observerList = Lists.newArrayList();
    }

    /**
     * 通知观察者
     *
     * @param dataDTO
     */
    protected abstract void notifyObservers(DataDTO dataDTO);

    /**
     * 添加观察者
     *
     * @param observer
     */
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 移除观察者
     *
     * @param observer
     */
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }
}
