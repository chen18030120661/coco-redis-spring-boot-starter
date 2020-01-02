package com.cxy.spring.boot.module.msgqueue.rabbit.ordinary;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 普通队列
 *
 * @Author xy.chen
 * @Date 2020/1/2
 */
@Configuration
public class OrdinaryConfig {
    @Bean
    public Queue ordinaryQueue() {
        return new Queue("ordinaryQueue");
    }
}
