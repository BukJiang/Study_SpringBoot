package com.buk.skywalking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.buk.skywalking.mapper")
@SpringBootApplication
public class SkywalkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkywalkingApplication.class, args);
    }

}
