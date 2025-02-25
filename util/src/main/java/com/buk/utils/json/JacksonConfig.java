package com.buk.utils.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO: Jackson配置
 *
 * @author jiangbk
 * @date 2020/12/30
 **/
public class JacksonConfig {

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // [简单模块]
        SimpleModule simpleModule = new SimpleModule();
        // Long
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        // Double
        simpleModule.addSerializer(Double.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Double.class, ToStringSerializer.instance);
        // BigDecimal
        simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        OBJECT_MAPPER.registerModule(simpleModule);

        // [时间模块]
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
        OBJECT_MAPPER.registerModule(javaTimeModule);

        // [序列化]
        // # 序列化Inclusion
//        // 若对象的属性值为null，序列化时不进行显示
//        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        // 若对象的属性值为""，序列化时不进行显示
//        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // # 设置属性命名策略
        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        // # 设置日期格式
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        // # 错误处理配置
        // 默认开启，若一个类没有public的方法或属性时，会导致序列化失败。若关闭会得到一个空Json串
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // # 特定数据类型序列化配置
        // 默认开启，将Date格式序列化为时间戳。
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 默认关闭，即以文本(ISO-8601)作为Key。开启后，以时间戳作为Key
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        // 默认开启，将Duration格式序列化为时间戳。
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        // 默认关闭，将char[]数组序列化为String类型。若开启后序列化为Json数组
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);

        // [反序列化]
        // # 错误处理配置
        // 默认关闭，不含有Json中的属性，则抛出异常。若开启则不解析该字段，而不会抛出异常
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // # value转换配置
        // 默认关闭，当Json字段为EMPTY_STRING时，解析为普通的对象抛出异常。若开启则该对象的属性值为null
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        // 默认关闭，当Json字段为空Json数组时，解析为普通的对象抛出异常。若开启则该对象的属性值为null
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        // 默认关闭，未知的Enum值将引发异常，若开启允许将未知的Enum值解析为空值的功能
        OBJECT_MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }

    protected JacksonConfig() {
    }
}
