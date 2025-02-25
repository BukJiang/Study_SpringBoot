package com.buk.designpattern.demo.behavioral.state;

/**
 * 【环境类】
 * - 也称为上下文，定义了客户端需要的接口，内部维护一个当前状态，并负责具体状态的切换
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public class Context {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.handle();
        context.handle();
    }

    /**
     * 【抽象状态】
     */
    private State state;

    public Context() {
        this.state = new ConcreteStateA();
    }

    /**
     * 获取状态
     *
     * @return
     */
    public State getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * 处理
     */
    public void handle() {
        state.handle(this);
    }
}
