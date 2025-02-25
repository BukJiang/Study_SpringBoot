package com.buk.designpattern.demo.structural.flyweight;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * 【享元工厂】
 * - 负责创建和管理享元角色。
 * - 当客户对象请求一个享元对象时，享元工厂检査系统中是否存在符合要求的享元对象，如果存在则提供给客户；如果不存在的话，则创建一个新的享元对象
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class FlyweightFactory {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        flyweightFactory.getFlyweight("A").operation(new UnsharedConcreteFlyweight("第1次调用A"));
        flyweightFactory.getFlyweight("A").operation(new UnsharedConcreteFlyweight("第2次调用A"));
        flyweightFactory.getFlyweight("B").operation(new UnsharedConcreteFlyweight("第1次调用B"));
        flyweightFactory.getFlyweight("B").operation(new UnsharedConcreteFlyweight("第2次调用B"));
    }

    /**
     * Map<key, 享元>
     */
    private Map<String, Flyweight> flyweightMap;

    public FlyweightFactory() {
        this.flyweightMap = Maps.newConcurrentMap();
    }

    /**
     * 获取享元
     *
     * @param key
     * @return
     */
    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = flyweightMap.get(key);
        if (Objects.isNull(flyweight)) {
            flyweight = new ConcreteFlyweight(key);
            flyweightMap.put(key, flyweight);
            log.info("[具体享元]key:{}, 被创建", key);
        } else {
            log.info("[具体享元]key:{}, 已存在", key);
        }
        return flyweight;
    }
}
