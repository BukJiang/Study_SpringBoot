package com.buk.mysql.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buk.mysql.pojo.entity.User;
import com.buk.mysql.mapper.UserMapper;
import com.buk.mysql.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * TODO: 缓存 @CacheConfig @Cacheable @CachePut @CacheEvict @Caching
 * <p>
 * 服务实现类
 * </p>
 *
 * @author BuK
 * @see com.buk.mysql.service.impl.UserServiceImplTest
 * @since 2020-08-17
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "CacheConfig:User")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Caching(cacheable = {
            @Cacheable(cacheNames = "CacheConfig:User:100s", key = "1"),
            @Cacheable(cacheNames = "CacheConfig:User:200s", key = "1"),
            @Cacheable(cacheNames = "CacheConfig:User:300s", key = "1")
    })
    public User getAndCacheByCacheName() {
        log.info("[缓存]-getAndCacheByCacheName: is sql");
        return userMapper.selectById(1);
    }

    @Override
    public User getAndCacheByCacheManager() {
        log.info("[缓存]-getAndCacheByCacheManager: is sql ");
        return userMapper.selectById(1);
    }

    @Override
    public User getAndCacheByDynamic() {
        log.info("[缓存]-getAndCacheByDynamic: is sql ");
        return userMapper.selectById(1);
    }

    @Override
    public void inset() {
        User user = new User(null, "1", 1);
        userMapper.insert(user);
    }

    @Override
    public void updateOne() {
        userMapper.updateById(new User(1L, "2", 2));
    }

    @Override
    public void delete() {
        userMapper.deleteById(1);
    }
}
