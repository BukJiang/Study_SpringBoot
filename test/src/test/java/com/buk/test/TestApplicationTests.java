package com.buk.test;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO: 测试
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestApplicationTests {

    @BeforeEach
    void beforeEach() {
        System.out.println("[测试]: BeforeEach");
    }

    @Test
    @Order(2)
    void test1() {
        System.out.println("[测试]: " + 1);
    }

    @Test
    @Order(1)
    void test2() {
        System.out.println("[测试]: " + 2);
    }
}
