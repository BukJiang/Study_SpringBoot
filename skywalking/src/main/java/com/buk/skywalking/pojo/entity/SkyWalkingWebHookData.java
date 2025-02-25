package com.buk.skywalking.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * SkyWalking WebHook 告警信息
 *
 * @author jiangbk
 * @date 2021/2/2
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkyWalkingWebHookData implements Serializable {

    private static final long serialVersionUID = 973934508018082779L;

    /**
     * 域Id
     */
    private Integer scopeId;

    /**
     * 域名称
     */
    private String scope;

    /**
     * 目标域的实体名称
     */
    private String name;

    /**
     * 目标域的实体ID
     */
    private String id0;

    /**
     * 未使用
     */
    private String id1;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 告警信息
     */
    private String alarmMessage;

    /**
     * 告警时间
     */
    private Long startTime;
}
