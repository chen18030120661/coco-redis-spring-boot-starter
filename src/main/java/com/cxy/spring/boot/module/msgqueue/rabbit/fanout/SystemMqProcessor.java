package com.cxy.spring.boot.module.msgqueue.rabbit.fanout;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EnableRabbit
@Component
public class SystemMqProcessor {

//    @RabbitListener(queues="#{aMessage.name}")
//    public void processA(String str){
//        System.out.println("ReceiverA:"+str);
//    }

//    @RabbitListener(queues="#{bMessage.name}")
//    public void processB(String str){
//        System.out.println("ReceiverB:"+str);
//    }

    @RabbitListener(queues="#{systemQueue.name}")
    public void receiver1(String str){
        System.out.println("ReceiverB:"+str);
    }
}
