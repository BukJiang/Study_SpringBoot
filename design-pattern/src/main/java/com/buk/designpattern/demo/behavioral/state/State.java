package com.buk.designpattern.demo.behavioral.state;

/**
 * 【抽象状态】
 * - 定义一个接口，用以封装环境对象中的特定状态所对应的行为，可以有一个或多个行为
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public abstract class State {

    /**
     * 处理
     *
     * @param context
     */
    public abstract void handle(Context context);
}
