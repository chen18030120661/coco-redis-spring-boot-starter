package com.cxy.spring.boot.module.msgqueue.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class TopicProcessor {
    @RabbitListener(queues="topic.a")
    public void receiver1(String str){
        System.out.println("AReceiver:"+str);
    }
    @RabbitListener(queues="topic.b")
    public void receiver2(String str){
        System.out.println("BReceiver:"+str);
    }
    @RabbitListener(queues="topic.c")
    public void receiver3(String str){
        System.out.println("CReceiver:"+str);
    }
}
