package com.cxy.spring.boot.module.msgqueue.rabbit.ordinary;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class OrdinaryProcessor {
    @RabbitListener(queues="ordinaryQueue")
    public void receiver1(String str){
        System.out.println("Receiver:"+str);
    }
}
