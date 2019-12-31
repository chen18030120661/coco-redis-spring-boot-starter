package com.cxy.spring.boot.module.msgqueue.rabbit.fanout;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RabbitSenderController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private FanoutExchange fanoutExchange;

    @PostMapping("/testRabbit")
    public void testRabbit()  {
        String[] tasks = {"111","222","333","444"};
        for(int i=0;i<tasks.length;i++){
            String content = tasks[i];
            rabbitTemplate.convertAndSend(fanoutExchange.getName(),"",content);
        }
    }
}
