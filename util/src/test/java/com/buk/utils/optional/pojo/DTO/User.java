package com.buk.utils.optional.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

/**
 * TODO: Optional 数据传输类
 *
 * @author BuK
 * @since 2020/09/04
 */
@Data
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;

    private Optional<String> optionalString;

    public User() {
        this.id = 1;
        this.name = UUID.randomUUID().toString();
    }
}
