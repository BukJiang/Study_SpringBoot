package com.buk.designpattern.demo.behavioral.observer;

import com.buk.designpattern.pojo.dto.DataDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * 【具体观察者】
 * - 实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
@Slf4j
public class ConcreteObserverB implements Observer {

    @Override
    public void execute(DataDTO dataDTO) {
        log.info("具体观察者B: {}", dataDTO);
    }
}
