package com.buk.springsecurity.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO: SpringSecurity 测试加密编码器
 */
@SpringBootTest
class UserDetailsServiceImplTest {

    @Test
    void test() {
        String a = "123456";
        String b = "{bcrypt}$2a$10$AR2ahS34k40QFlLRu0c/keSxrwA/K0e/FrUgvNfUT4LZ84MOP7PNq";

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches1 = bCryptPasswordEncoder.matches(a, b);
        System.out.println(matches1);

        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        boolean matches2 = delegatingPasswordEncoder.matches(a, b);
        System.out.println(matches2);
    }
}