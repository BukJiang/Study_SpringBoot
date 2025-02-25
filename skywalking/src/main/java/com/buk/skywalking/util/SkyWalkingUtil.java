package com.buk.skywalking.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;

/**
 * TODO: SkyWalking工具
 *
 * @author jiangbk
 * @date 2020/11/19
 **/
@Slf4j
public class SkyWalkingUtil {

    @Trace
    @Tags({
            @Tag(key = "indexKey1", value = "value1"),
            @Tag(key = "indexKey2", value = "value2")
    })
    public static String indexUtil(String paramA, String paramB) {
        SkyWalkingUtil skyWalkingUtil = new SkyWalkingUtil();
        String baseUtil = skyWalkingUtil.baseUtil(paramA, paramB);
        return "[SkyWalking工具]-" + baseUtil;
    }

    public static String exceptionUtil(String paramA, String paramB) {
        throw new RuntimeException("[SkyWalking工具]");
    }

    @Trace
    @Tags({
            @Tag(key = "baseKey1", value = "arg[0]"),
            @Tag(key = "baseKey2", value = "arg[1]")
    })
    public String baseUtil(String paramA, String paramB) {
        return "[SkyWalking工具BaseUtil]";
    }
}
