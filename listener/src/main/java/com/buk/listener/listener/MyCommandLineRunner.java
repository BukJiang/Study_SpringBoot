package com.buk.listener.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO: 事件监听（SpringBoot容器加载之后执行，执行完成后项目启动完成）
 *
 * @author BuK
 * @since 2020/08/18
 */
@Component
@Order(value = 1) // @Order注解，value值越小越先执行，
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("[事件监听]-CommandLineRunner-: " +
                "Class implements implements CommandLineRunner - run(String... args)");
    }
}
