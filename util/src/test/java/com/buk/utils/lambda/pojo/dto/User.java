package com.buk.utils.lambda.pojo.dto;

import lombok.Data;

import java.util.UUID;

/**
 * TODO: lambda 数据类
 *
 * @author jiangbk
 * @date 2021/1/5
 **/
@Data
public class User {

    private String name;

    public User() {
        this.name = UUID.randomUUID().toString().substring(0, 3);
    }
}
