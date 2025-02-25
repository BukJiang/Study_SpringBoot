package com.buk.elasticsearch.pojo.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

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
@Document(indexName = "buk_user", shards = 3, replicas = 1, createIndex = true)
public class User {

    @Id
    @Field(type = FieldType.Keyword)
    private String myId;

    @Field(type = FieldType.Keyword)
    private String myKeyword;

    @Field(type = FieldType.Keyword, ignoreAbove = 0)
    private String myKeywordIgnoreAbove;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String myText;

    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String myTextInnerKeyword;

    @Field(type = FieldType.Boolean)
    private Boolean myBoolean;

    @Field(type = FieldType.Integer)
    private Integer myInteger;

    @Field(type = FieldType.Long)
    private Long myLong;

    @Field(type = FieldType.Float)
    private Float myFloat;

    @Field(type = FieldType.Double)
    private Double myDouble;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime myDate;
}
