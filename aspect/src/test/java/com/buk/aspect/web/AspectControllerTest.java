package com.buk.aspect.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO: 切面控制器测试
 */
@SpringBootTest
class AspectControllerTest {

    @Autowired
    private AspectController aspectController;

    @Test
    void execute() throws InterruptedException {
        String execute = aspectController.execute();
        System.out.println("[切面控制器测试]: " + execute);
    }
}