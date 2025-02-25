package com.buk.aspect.web;

import com.buk.aspect.service.AspectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 切面控制器
 *
 * @author BuK
 * @see com.buk.aspect.web.AspectControllerTest
 * @since 2020/08/19
 */
@Slf4j
@RestController
@RequestMapping("/aspect")
public class AspectController {

    @Autowired
    private AspectService aspectService;

    @RequestMapping("")
    public String execute() throws InterruptedException {
        Thread.sleep(1000L);
        log.info("[切面控制器]: AspectController.execute->返回");
        return "[切面控制器]: AspectController.execute->返回";
    }

    @RequestMapping("/doService")
    public String doService() {
        String execute1 = aspectService.execute(false);
        log.info(execute1);
        String execute2 = aspectService.execute(true);
        log.info(execute2);
        return "[切面控制器]: AspectController.doService->返回";
    }
}
