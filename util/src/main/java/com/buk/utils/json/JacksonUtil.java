package com.buk.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * TODO: Jackson工具
 *
 * @author jiangbk
 * @date 2020/12/30
 **/
@Slf4j
public class JacksonUtil extends JacksonConfig {

    /**
     * @param object
     * @return
     */
    public static String objToJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("[Jackson工具] objToJson error! Object:{}, Exception:{}", object, e);
            return null;
        }
    }

    /**
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T jsonToObj(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("[Jackson工具] jsonToObj error! Json:{}, Class:{}, Exception:{}", json, tClass, e);
            return null;
        }
    }

    /**
     * @param json
     * @return
     */
    public static JsonNode jsonToJsonNode(String json) {
        try {
            return OBJECT_MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("[Jackson工具] jsonToJsonNode error! Json:{}, Exception:{}", json, e);
            return null;
        }
    }

    /**
     * @param jsonNode
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T jsonNodeToObj(JsonNode jsonNode, Class<T> tClass) {
        return convertValue(jsonNode, tClass);
    }

    /**
     * @param object
     * @return
     */
    public static JsonNode objToJsonNode(Object object) {
        return convertValue(object, JsonNode.class);
    }

    /**
     * @param json
     * @param keyClass
     * @param valueClass
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json, Class<K> keyClass, Class<V> valueClass) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, keyClass, valueClass);
        return readValue(json, javaType);
    }

    /**
     * @param map
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T, K, V> T mapToObj(Map<K, V> map, Class<T> tClass) {
        return convertValue(map, tClass);
    }

    /**
     * @param object
     * @param keyClass
     * @param valueClass
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> objToMap(Object object, Class<K> keyClass, Class<V> valueClass) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, keyClass, valueClass);
        return convertValue(object, javaType);
    }

    /**
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToObjList(String json, Class<T> tClass) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, tClass);
        return readValue(json, javaType);
    }

    /**
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> Set<T> jsonToObjSet(String json, Class<T> tClass) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(HashSet.class, tClass);
        return readValue(json, javaType);
    }

    /**
     * @param json
     * @param javaType
     * @param <T>
     * @return
     */
    private static <T> T readValue(String json, JavaType javaType) {
        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            log.error("[Jackson工具] readValue error! Json:{}, JavaType:{}, Exception:{}", json, javaType, e);
            return null;
        }
    }

    /**
     * @param object
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T convertValue(Object object, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.convertValue(object, tClass);
        } catch (IllegalArgumentException e) {
            log.error("[Jackson工具] readValue error! Object:{}, Class:{}, Exception:{}", object, tClass, e);
            return null;
        }
    }

    /**
     * @param object
     * @param javaType
     * @param <T>
     * @return
     */
    private static <T> T convertValue(Object object, JavaType javaType) {
        try {
            return OBJECT_MAPPER.convertValue(object, javaType);
        } catch (IllegalArgumentException e) {
            log.error("[Jackson工具] readValue error! Object:{}, JavaType:{}, Exception:{}", object, javaType, e);
            return null;
        }
    }

    private JacksonUtil() {
    }
}
