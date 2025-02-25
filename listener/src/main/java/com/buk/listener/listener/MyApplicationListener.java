package com.buk.listener.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * TODO: 事件监听（ContextRefreshedEvent，所有Singleton Bean 被预实例化，ApplicationContext容器已就绪可用）
 *
 * @author BuK
 * @since 2020/08/18
 */
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("[事件监听]-MyApplicationListener-: " +
                "Class implements ApplicationListener<ContextRefreshedEvent> - onApplicationEvent");

        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            System.out.println("[事件监听]-MyApplicationListener-: 所有bean都加载完成，SpringBoot初始化完毕");

        }
    }
}
