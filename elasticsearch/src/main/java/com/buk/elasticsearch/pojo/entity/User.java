package com.buk.elasticsearch.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * TODO: Elasticsearch实体类
 *
 * @author BuK
 * @since 2020/09/04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "index_1")
public class User {

    @Id
    private Object id;

    @Field(type = FieldType.Keyword)
    private String nameKeyword;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String nameText;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime date;

}
