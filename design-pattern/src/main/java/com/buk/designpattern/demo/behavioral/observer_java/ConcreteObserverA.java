package com.buk.designpattern.demo.behavioral.observer_java;

import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * 【具体观察者】
 * - 实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
@Slf4j
public class ConcreteObserverA implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        log.info("具体观察者A: {}", arg);
    }
}
