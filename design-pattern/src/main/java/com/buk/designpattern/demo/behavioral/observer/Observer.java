package com.buk.designpattern.demo.behavioral.observer;

import com.buk.designpattern.pojo.dto.DataDTO;

/**
 * 【抽象观察者】
 * - 一个抽象类或接口
 * - 包含更新自己的抽象方法，当接到具体主题的更改通知时被调用
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
public interface Observer {

    /**
     * 执行
     *
     * @param dataDTO
     */
    void execute(DataDTO dataDTO);
}
