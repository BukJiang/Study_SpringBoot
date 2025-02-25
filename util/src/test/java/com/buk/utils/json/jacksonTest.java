package com.buk.utils.json;

import com.buk.utils.json.pojo.dto.User;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO: jackson
 */
@Slf4j
@SpringBootTest
class jacksonTest {

    @Test
    void test() {
        // 单个
        User user = new User(true);
        String userJson = JacksonUtil.objToJson(user);
        User user1 = JacksonUtil.jsonToObj(userJson, User.class);
        JsonNode jsonNode1 = JacksonUtil.jsonToJsonNode(userJson);
        User user2 = JacksonUtil.jsonNodeToObj(jsonNode1, User.class);
        JsonNode jsonNode2 = JacksonUtil.objToJsonNode(user2);
        Map<String, Object> stringObjectMap1 = JacksonUtil.jsonToMap(userJson, String.class, Object.class);
        User user3 = JacksonUtil.mapToObj(stringObjectMap1, User.class);
        Map<String, Object> stringObjectMap2 = JacksonUtil.objToMap(user3, String.class, Object.class);
        // 列表
        List<User> userList = Lists.newArrayList(user, user, user);
        String userListJson = JacksonUtil.objToJson(userList);
        JsonNode jsonNode11 = JacksonUtil.jsonToJsonNode(userListJson);
        List<User> userList11 = JacksonUtil.jsonToObjList(userListJson, User.class);
        Set<User> userSet11 = JacksonUtil.jsonToObjSet(userListJson, User.class);
        //
        System.out.println();
    }
}
