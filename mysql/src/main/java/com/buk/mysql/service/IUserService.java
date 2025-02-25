package com.buk.mysql.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buk.mysql.pojo.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author BuK
 * @since 2020-08-17
 */
public interface IUserService extends IService<User> {

    /**
     * 获取
     *
     * @return
     */
    User getAndCacheByCacheName();

    /**
     * 获取
     *
     * @return
     */
    User getAndCacheByCacheManager();

    /**
     * 获取
     *
     * @return
     */
    User getAndCacheByDynamic();

    /**
     * 新增
     */
    void inset();

    /**
     * 更新
     */
    void updateOne();

    /**
     * 删除
     */
    void delete();
}
