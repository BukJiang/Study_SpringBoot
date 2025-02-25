package com.buk.validator.web;

import com.buk.validator.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 验证器控制器
 *
 * @author BuK
 * @since 2020/08/17
 */
@Slf4j
@RestController
@RequestMapping("/validator")
public class ValidatorController {

    @PostMapping("insert")
    public void validatorInsert(@Validated(User.Insert.class) @RequestBody User user) {
        log.info("[验证器控制器]-Insert: {}", user);
    }

    @PostMapping("update")
    public void validatorUpdate(@Validated(User.Update.class) @RequestBody User user) {
        log.info("[验证器控制器]-Update: {}", user);
    }
}
