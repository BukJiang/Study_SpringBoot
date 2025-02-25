package com.buk.designpattern.demo.behavioral.mediator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 【具体中介者】
 * - 实现中介者接口，定义一个List来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public class ConcreteMediator extends Mediator {

    /**
     * 同事集合
     */
    private List<Colleague> colleagueList;

    public ConcreteMediator() {
        this.colleagueList = Lists.newArrayList();
    }

    /**
     * 注册
     *
     * @param colleague
     */
    @Override
    public void register(Colleague colleague) {
        if (!colleagueList.contains(colleague)) {
            colleagueList.add(colleague);
            colleague.setMediator(this);
        }
    }

    /**
     * 转发
     *
     * @param colleague
     * @param info
     */
    @Override
    public void relay(Colleague colleague, String info) {
        colleagueList.stream()
                .filter(x -> !x.equals(colleague))
                .forEach(x -> x.receive(info));
    }
}
