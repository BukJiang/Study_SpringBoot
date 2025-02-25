package com.buk.mongodb.pojo.entity.testdb2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * TODO: MongoDB实体类
 *
 * @author BuK
 * @since 2020/08/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@CompoundIndexes({
        @CompoundIndex(name = "users_name", def = "{'name':1}"),
        @CompoundIndex(name = "users_age", def = "{'age':-1}"),
})
public class User {

    @MongoId
    private String id;

    private String name;

    @Indexed
    private String sex;

    @Indexed
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @Transient
    private String transientData;
}
