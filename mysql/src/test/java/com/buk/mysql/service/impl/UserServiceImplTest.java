package com.buk.mysql.service.impl;

import com.buk.mysql.pojo.entity.User;
import com.buk.mysql.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO: 缓存测试
 */
@Slf4j
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService iUserService;

    @Test
    void get() {
        User user1 = iUserService.getAndCacheByCacheName();
        log.info("[测试缓存]: {}", user1);
        User user2 = iUserService.getAndCacheByCacheName();
        log.info("[测试缓存]: {}", user2);

        User user3 = iUserService.getAndCacheByCacheManager();
        log.info("[测试缓存]: {}", user3);
        User user4 = iUserService.getAndCacheByCacheManager();
        log.info("[测试缓存]: {}", user4);

        User user5 = iUserService.getAndCacheByDynamic();
        log.info("[测试缓存]: {}", user5);
        User user6 = iUserService.getAndCacheByDynamic();
        log.info("[测试缓存]: {}", user6);
    }

    @Test
    void inset() {
        iUserService.inset();
    }

    @Test
    void updateOne() {
        iUserService.updateOne();
    }

    @Test
    void delete() {
        iUserService.delete();
    }
}