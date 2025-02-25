package com.buk.mysql.service.demo1.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buk.mysql.pojo.entity.demo1.User;
import com.buk.mysql.mapper.demo.Demo1UserMapper;
import com.buk.mysql.service.demo1.IUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author BuK
 * @see com.buk.mysql.service.impl.MoreDemoUserServiceImplTest
 * @since 2020-08-17
 */
//@Service("DemoUserServiceImpl")
public class UserServiceImpl extends ServiceImpl<Demo1UserMapper, User> implements IUserService {

}
