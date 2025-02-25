package com.buk.skywalking.service.impl;

import com.buk.skywalking.mapper.UserMapper;
import com.buk.skywalking.pojo.entity.User;
import com.buk.skywalking.service.SkyWalkingService;
import com.buk.skywalking.util.SkyWalkingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * TODO: SkyWalking服务Impl
 *
 * @author jiangbk
 * @date 2020/11/19
 **/
@Slf4j
@Service
public class SkyWalkingServiceImpl implements SkyWalkingService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Trace
    @Tag(key = "indexService.paramA", value = "arg[0]")
    @Tag(key = "indexService.paramB", value = "arg[1]")
    @Tag(key = "indexService.returnedObj", value = "returnedObj")
    public String indexService(String paramA, String paramB) {
        String util = SkyWalkingUtil.indexUtil(paramA, paramB);
        String baseService = baseService(paramA, paramB);
        return "[SkyWalking服务Impl]-" + baseService + util;
    }

    @Override
    public String mysqlService(String paramA, String paramB) {
        User user = new User();
        user.setName("NAME_" + UUID.randomUUID().toString());
        user.setOtherName("OTHER_NAME_" + UUID.randomUUID().toString());
        userMapper.insert(user);
        return "[SkyWalking服务Impl: " + user + "]";
    }

    @Override
    public String exceptionService(String paramA, String paramB) {
        String util = SkyWalkingUtil.exceptionUtil(paramA, paramB);
        return "[SkyWalking服务Impl]-" + util;
    }

    public String baseService(String paramA, String paramB) {
        return "[SkyWalking服务BaseService]-";
    }
}
