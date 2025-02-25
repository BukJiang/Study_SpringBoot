package com.buk.skywalking.web;

import com.buk.skywalking.service.SkyWalkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * TODO: SkyWalking-链路控制器
 *
 * @author jiangbk
 * @date 2020/11/18
 **/
@Slf4j
@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private SkyWalkingService skyWalkingService;

    @PostMapping("/index")
    public String index(@RequestParam String paramA, @RequestParam String paramB) {
        String service = skyWalkingService.indexService(paramA, paramB);
        return "[SkyWalking控制器]-" + service;
    }

    @PostMapping("/mysql")
    public String mysql(@RequestParam String paramA, @RequestParam String paramB) {
        String service = skyWalkingService.mysqlService(paramA, paramB);
        return "[SkyWalking控制器]-" + service;
    }

    @PostMapping("/exception")
    public String exception(@RequestParam String paramA, @RequestParam String paramB) {
        String service = skyWalkingService.exceptionService(paramA, paramB);
        return "[SkyWalking控制器]-" + service;
    }

    @PostMapping("/randomSleep")
    public String randomSleep(@RequestParam String paramA, @RequestParam String paramB) throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(500));
        String service = skyWalkingService.indexService(paramA, paramB);
        Thread.sleep(random.nextInt(500));
        return "[SkyWalking控制器]-" + service;
    }
}
