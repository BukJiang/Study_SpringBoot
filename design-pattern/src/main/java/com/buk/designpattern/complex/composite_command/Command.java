package com.buk.designpattern.complex.composite_command;

/**
 * 【抽象命令类】
 * - 声明执行命令的接口，拥有执行命令的抽象方法 execute()
 *
 * @author jiangbk
 * @date 2021/3/17
 **/
public interface Command {

    /**
     * 执行
     */
    void execute();
}
