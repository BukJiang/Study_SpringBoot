package com.buk.mysql.service.impl;

import com.buk.mysql.mapper.demo.Demo1UserMapper;
import com.buk.mysql.mapper.demo2.Demo2UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO: 多数据源测试
 */
@Slf4j
@SpringBootTest
class MoreDemoUserServiceImplTest {

    @Autowired
    private Demo1UserMapper demo1UserMapper;

    @Autowired
    private Demo2UserMapper demo2UserMapper;

    @Test
    void get() {
        com.buk.mysql.pojo.entity.demo1.User user1 = demo1UserMapper.selectById(1);
        log.info("[多数据源测试]: {}", user1);

        com.buk.mysql.pojo.entity.demo2.User user2 = demo2UserMapper.selectById(2);
        log.info("[多数据源测试]: {}", user2);
    }
}