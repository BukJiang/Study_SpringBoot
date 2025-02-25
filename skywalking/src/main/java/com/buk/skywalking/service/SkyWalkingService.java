package com.buk.skywalking.service;

/**
 * TODO: SkyWalking服务
 *
 * @author jiangbk
 * @date 2020/11/19
 **/
public interface SkyWalkingService {

    String indexService(String paramA, String paramB);

    String mysqlService(String paramA, String paramB);

    String exceptionService(String paramA, String paramB);
}
