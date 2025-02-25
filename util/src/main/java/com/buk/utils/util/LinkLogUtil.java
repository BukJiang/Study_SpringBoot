package com.buk.utils.util;

import com.buk.utils.json.JacksonUtil;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO: 链路日志工具
 *
 * @author jiangbk
 * @date 2021/3/2
 **/
@Data
@Slf4j
public class LinkLogUtil {

    /**
     * 字符串拼接符
     */
    private static final String STR_JOIN = ": ";
    /**
     * 时间节点拼接符
     */
    private static final String TIME_JOIN = ".";
    /**
     * 层级拼接符
     */
    private static final String LEVEL_JOIN = "    ";
    /**
     * 换行符
     */
    private static final String NEWLINE = "\n";
    private static final Integer UUID_BEGIN_INDEX = 33;
    private static final String UNKNOWN_VALUE_TYPE = "未知Value类型枚举: ";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 定点时间
     */
    private Long fixedTime = System.currentTimeMillis();
    /**
     * 日志Map
     */
    private LinkedHashMap<String, Object> logMap = Maps.newLinkedHashMap();

    /**
     * 定点时间
     *
     * @return
     */
    public LinkLogUtil fixedTime() {
        fixedTime = System.currentTimeMillis();
        return this;
    }

    /**
     * 记录
     *
     * @param value
     * @return
     */
    public LinkLogUtil put(Object value) {
        String localDateTime = LocalDateTime.now().format(DTF) + TIME_JOIN + UUID.randomUUID().toString().substring(UUID_BEGIN_INDEX);
        return put(localDateTime, value);
    }

    /**
     * 记录
     *
     * @param key
     * @param value
     * @return
     */
    public LinkLogUtil put(String key, Object value) {
        if (logMap.containsKey(key)) {
            throw new LinkLogException("【链路日志工具】已存在key不能再一次记录！");
        }
        logMap.put(key, value);
        return this;
    }

    /**
     * 记录
     *
     * @param key
     * @param valueTypeEnum
     * @return
     */
    public LinkLogUtil put(String key, ValueTypeEnum valueTypeEnum) {
        switch (valueTypeEnum) {
            case TIME_CONSUMING:
                put(key, valueTypeEnum.description + (System.currentTimeMillis() - fixedTime));
                break;
            case DATE_TIME:
                put(key, valueTypeEnum.description + LocalDateTime.now().format(DTF));
                break;
            default:
                put(key, UNKNOWN_VALUE_TYPE + valueTypeEnum);
                break;
        }
        return this;
    }

    /**
     * 转Map
     *
     * @return
     */
    public Map<String, Object> toMap() {
        return logMap;
    }

    /**
     * 转Str
     *
     * @return
     */
    public String toStr() {
        StringBuilder logStr = new StringBuilder();
        toStr(this, logStr, "");
        return logStr.toString();
    }

    /**
     * @param linkLogUtil
     * @param logStr
     * @param blank
     */
    private void toStr(LinkLogUtil linkLogUtil, StringBuilder logStr, String blank) {
        linkLogUtil.toMap().forEach((key, value) -> {
            try {
                LinkLogUtil subLinkLogUtil = (LinkLogUtil) value;
                toStr(subLinkLogUtil, logStr, blank + LEVEL_JOIN);
            } catch (Exception e) {
                logStr.append(blank).append(key).append(STR_JOIN).append(value).append(NEWLINE);
            }
        });
    }

    /**
     * 构建器
     *
     * @return
     */
    public static LinkLogUtil builder() {
        return new LinkLogUtil();
    }

    /**
     * Value类型枚举
     */
    public enum ValueTypeEnum {
        //
        TIME_CONSUMING("耗时: "),
        DATE_TIME("时间: ");

        public final String description;

        ValueTypeEnum(String description) {
            this.description = description;
        }
    }

    /**
     * 链路日志异常
     */
    private static class LinkLogException extends RuntimeException {

        public LinkLogException(String message) {
            super(message);
        }

        public LinkLogException(Throwable cause) {
            super(cause);
        }

        public LinkLogException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * 示例
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkLogUtil xxx = LinkLogUtil.builder()
                .fixedTime()
                .put(111)
                .put(222)
                .put(333)
                .put("第一阶段", ValueTypeEnum.TIME_CONSUMING)
                .fixedTime()
                .put("111", UUID.randomUUID().toString())
                .put("222", UUID.randomUUID().toString())
                .put("333", UUID.randomUUID().toString())
                .put("第二阶段", ValueTypeEnum.TIME_CONSUMING)
                .fixedTime()
                .put(
                        UUID.randomUUID().toString(),
                        LinkLogUtil.builder()
                                .fixedTime()
                                .put(111)
                                .put(222)
                                .put(333)
                                .put("第一阶段", ValueTypeEnum.TIME_CONSUMING)
                                .fixedTime()
                                .put("111", UUID.randomUUID().toString())
                                .put("222", UUID.randomUUID().toString())
                                .put("333", UUID.randomUUID().toString())
                                .put("第二阶段", ValueTypeEnum.TIME_CONSUMING)
                )
                .put("第三阶段", ValueTypeEnum.TIME_CONSUMING);

        LinkLogUtil linkLogUtil = LinkLogUtil.builder()
                .fixedTime()
                .put(111)
                .put(222)
                .put(333)
                .put("第一阶段", ValueTypeEnum.TIME_CONSUMING)
                .fixedTime()
                .put("111", UUID.randomUUID().toString())
                .put("222", UUID.randomUUID().toString())
                .put("333", UUID.randomUUID().toString())
                .put("第二阶段", ValueTypeEnum.TIME_CONSUMING)
                .fixedTime()
                .put(
                        UUID.randomUUID().toString(),
                        LinkLogUtil.builder()
                                .fixedTime()
                                .put(111)
                                .put(222)
                                .put(333)
                                .put("第一阶段", ValueTypeEnum.TIME_CONSUMING)
                                .fixedTime()
                                .put("111", UUID.randomUUID().toString())
                                .put("222", UUID.randomUUID().toString())
                                .put("333", UUID.randomUUID().toString())
                                .put("第二阶段", ValueTypeEnum.TIME_CONSUMING)
                )
                .put("第三阶段", ValueTypeEnum.TIME_CONSUMING);

        Map<String, Object> stringObjectMap = linkLogUtil.toMap();
        log.info("【链路日志工具】{}", JacksonUtil.objToJson(stringObjectMap));

        String s = linkLogUtil.toStr();
        log.info("【链路日志工具】{}", s);
    }
}