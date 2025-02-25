package com.buk.properties.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * TODO: 属性配置 @ConfigurationProperties
 *
 * @author BuK
 * @see com.buk.properties.property.PropertiesTest
 * @since 2020/08/16
 */
@Data
@Component
@ConfigurationProperties(prefix = "my-properties")
public class MyProperties {

    private String paramString;

    private List<String> paramStringList1;

    private List<String> paramStringList2;

    private Map<String, String> paramMap;
}
