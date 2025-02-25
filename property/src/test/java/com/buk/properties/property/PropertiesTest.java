package com.buk.properties.property;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO: 属性配置测试
 */
@SpringBootTest
class PropertiesTest {

    @Value("${value.param1}")
    private String value_param1;

    @Value("${value.param1_2}")
    private String value_param1_2;

    @Value("${value.randomString}")
    private String value_randomString;

    @Value("${value.randomUUID}")
    private String value_randomUUID;

    @Value("${value.randomInt1}")
    private String value_randomInt1;

    @Value("${value.randomInt2}")
    private String value_randomInt2;

    @Value("${value.randomLong1}")
    private String value_randomLong1;

    @Value("${value.randomLong2}")
    private String value_randomLong2;

    @Value("${server.port}")
    private Integer server_port;

    @Autowired
    private MyProperties myProperties;

    @Test
    void getValue() {
        System.out.println("[属性配置]: " + value_param1);
        System.out.println("[属性配置]: " + value_param1_2);
        System.out.println("[属性配置]: " + value_randomString);
        System.out.println("[属性配置]: " + value_randomUUID);
        System.out.println("[属性配置]: " + value_randomInt1);
        System.out.println("[属性配置]: " + value_randomInt2);
        System.out.println("[属性配置]: " + value_randomLong1);
        System.out.println("[属性配置]: " + value_randomLong2);
        System.out.println("[属性配置]: " + server_port);
    }

    @Test
    void myProperties() {
        System.out.println("[属性配置]: " + myProperties.getParamString());
        System.out.println("[属性配置]: " + myProperties.getParamStringList1());
        System.out.println("[属性配置]: " + myProperties.getParamStringList2());
        System.out.println("[属性配置]: " + myProperties.getParamMap());
    }
}