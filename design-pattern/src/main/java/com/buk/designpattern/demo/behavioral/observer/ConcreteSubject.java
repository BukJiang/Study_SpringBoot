package com.buk.designpattern.demo.behavioral.observer;

import com.buk.designpattern.pojo.dto.DataDTO;

import java.util.Optional;

/**
 * 【具体主题】
 * - 也叫具体目标类
 * - 实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
public class ConcreteSubject extends Subject {

    @Override
    protected void notifyObservers(DataDTO dataDTO) {
        Optional.ofNullable(observerList)
                .ifPresent(observerList -> observerList.forEach(observer -> observer.execute(dataDTO)));
    }
}
