package com.buk.designpattern.demo.behavioral.chain_of_responsibility;

/**
 * 【抽象处理者】
 * - 定义一个处理请求的接口，包含抽象处理方法和一个后继连接
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public abstract class Handler {

    /**
     * 【抽象处理者】
     */
    private Handler next;

    /**
     * 获取下一抽象处理者
     *
     * @return
     */
    public Handler getNext() {
        return next;
    }

    /**
     * 设置下一抽象处理者
     *
     * @param next
     */
    public void setNext(Handler next) {
        this.next = next;
    }

    /**
     * 处理
     *
     * @param request
     * @return
     */
    public abstract String handle(String request);
}
