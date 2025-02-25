package com.buk.service.web;

import com.buk.service.pojo.entity.User;
import com.buk.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * TODO: Service接口
 *
 * @author BuK
 * @since 2020/08/17
 */
@Slf4j
@RestController
@RequestMapping("/service")
public class ServiceController {

    private final UserService user1Service;

    private final UserService user2Service;

    private final Map<String, UserService> userServiceMap;

    public ServiceController(
            @Qualifier("User1ServiceImpl") UserService user1Service,
            @Qualifier("User2ServiceImpl") UserService user2Service,
            Map<String, UserService> userServiceMap
    ) {
        this.user1Service = user1Service;
        this.user2Service = user2Service;
        this.userServiceMap = userServiceMap;
    }

    /**
     * Qualifier
     *
     * @return
     */
    @GetMapping("getUserByQualifier")
    public void getUserByQualifier() {
        User user1 = user1Service.getUser();
        log.info("[getUserByQualifier] user1:{}", user1);

        User user2 = user2Service.getUser();
        log.info("[getUserByQualifier] user2:{}", user2);
    }

    /**
     * ServiceMap
     *
     * @return
     */
    @GetMapping("getUserByServiceMap")
    public void getUserByServiceMap() {
        User user1 = userServiceMap.get("User1ServiceImpl").getUser();
        log.info("[getUserByServiceMap] user1:{}", user1);

        User user2 = userServiceMap.get("User2ServiceImpl").getUser();
        log.info("[getUserByServiceMap] user2:{}", user2);
    }
}
