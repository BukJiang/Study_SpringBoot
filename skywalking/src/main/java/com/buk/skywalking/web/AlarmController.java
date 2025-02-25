package com.buk.skywalking.web;

import com.alibaba.fastjson.JSONObject;
import com.buk.skywalking.pojo.entity.SkyWalkingWebHookData;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO: SkyWalking-告警控制器
 *
 * @author jiangbk
 * @date 2020/11/18
 * @see ./config/alarm-settings.yml
 **/
@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController {

    /**
     * 服务响应时间规则
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/serviceRespTimeRule")
    public String serviceRespTimeRule() throws InterruptedException {
        Thread.sleep(500);
        return "服务响应时间规则";
    }

    /**
     * 服务Sla规则
     *
     * @return
     */
    @RequestMapping("/serviceSlaRule")
    public String serviceSlaRule() {
        throw new RuntimeException("服务Sla规则");
    }

    /**
     * 网络钩子
     *
     * @param skyWalkingWebHookDataList
     * @return
     */
    @RequestMapping("/webHook")
    public void webHook(@RequestBody List<SkyWalkingWebHookData> skyWalkingWebHookDataList) {
        log.info("[网络钩子] - {}", JSONObject.toJSONString(skyWalkingWebHookDataList));
        //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AtomicReference<String> contentAtomic = new AtomicReference<>("");
        skyWalkingWebHookDataList.forEach(skyWalkingWebHookData -> {
            contentAtomic.set(contentAtomic.get() + "\n");
            contentAtomic.set(contentAtomic.get() + "\n【域Id】: " + skyWalkingWebHookData.getScopeId());
            contentAtomic.set(contentAtomic.get() + "\n【域名称】: " + skyWalkingWebHookData.getScope());
            contentAtomic.set(contentAtomic.get() + "\n【目标域的实体ID】: " + skyWalkingWebHookData.getId0());
            contentAtomic.set(contentAtomic.get() + "\n【目标域的实体名称】: " + skyWalkingWebHookData.getName());
            contentAtomic.set(contentAtomic.get() + "\n【未使用】: " + skyWalkingWebHookData.getId1());
            contentAtomic.set(contentAtomic.get() + "\n【规则名称】: " + skyWalkingWebHookData.getRuleName());
            contentAtomic.set(contentAtomic.get() + "\n【告警信息】: " + skyWalkingWebHookData.getAlarmMessage());
            contentAtomic.set(contentAtomic.get() + "\n【告警时间】: " + sdf.format(new Date(skyWalkingWebHookData.getStartTime())));
        });
        dingDingNotice(contentAtomic.get());
    }

    /**
     * 钉钉通知
     *
     * @param content
     */
    private void dingDingNotice(String content) {
        String url = "https://oapi.dingtalk.com/robot/send?access_token=0cddecedd823463c73d79248979129d617b822fa9ae3dc34a64a33f518ea09cd";
        // 发起请求
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        requestHeaders.add("charset", "utf-8");
        HashMap<Object, Object> textParam = Maps.newHashMap();
        textParam.put("content", "—————————TEST—————————" + content);
        HashMap<Object, Object> msgParam = Maps.newHashMap();
        msgParam.put("msgtype", "text");
        msgParam.put("text", textParam);
        //
        HttpEntity<Map<Object, Object>> httpEntity = new HttpEntity<>(msgParam, requestHeaders);
        Object forObject = restTemplate.postForObject(url, httpEntity, Object.class);
        log.info("[url] - " + url);
        log.info("[result] - " + forObject);
    }
}
