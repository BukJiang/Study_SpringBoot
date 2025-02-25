package com.buk.mysql.service.demo2.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buk.mysql.pojo.entity.demo2.User;
import com.buk.mysql.mapper.demo2.Demo2UserMapper;
import com.buk.mysql.service.demo2.IUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author BuK
 * @see com.buk.mysql.service.impl.MoreDemoUserServiceImplTest
 * @since 2020-08-17
 */
//@Service("Demo2UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<Demo2UserMapper, User> implements IUserService {

}
