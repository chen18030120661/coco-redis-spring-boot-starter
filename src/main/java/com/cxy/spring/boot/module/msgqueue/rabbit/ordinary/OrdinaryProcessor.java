package com.cxy.spring.boot.module.msgqueue.rabbit.ordinary;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class OrdinaryProcessor {
//    @RabbitListener(queues="ordinaryQueue")
    // 通过注解自动创建 spring.simple.queue 队列
    @RabbitListener(queuesToDeclare = @Queue("ordinaryQueue"))
    public void receiver1(String str){
        System.out.println("1Receiver:"+str);
    }

//    @RabbitListener(queues="ordinaryQueue")
//    // 通过注解自动创建 spring.simple.queue 队列
////    @RabbitListener(queuesToDeclare = @Queue("ordinaryQueue"))
//    public void receiver2(String str){
//        System.out.println("2Receiver:"+str);
//    }
}
