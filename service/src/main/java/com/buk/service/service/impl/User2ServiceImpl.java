package com.buk.service.service.impl;

import com.buk.service.pojo.entity.User;
import com.buk.service.service.UserService;
import org.springframework.stereotype.Service;

/**
 * TODO: Service服务实现类
 *
 * @author jiangbk
 * @date 2021/1/11
 **/
@Service("User2ServiceImpl")
public class User2ServiceImpl implements UserService {

    @Override
    public User getUser() {
        User user = new User();
        user.setId(2L);
        return user;
    }
}
