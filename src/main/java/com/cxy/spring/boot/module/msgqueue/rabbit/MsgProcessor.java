package com.cxy.spring.boot.module.msgqueue.rabbit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cxy.spring.boot.module.msgqueue.MsgQueueEntity;
import com.cxy.spring.boot.test.redis.TestService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@EnableRabbit
public class MsgProcessor {
    public static final String QUEUE = "msg.test.insert.queue";
    public static final String EXCHANGE = "msg.test.insert.exchange";
    public static final String RUTE_KEY = "msg.test.insert.rutekey";
    @Resource
    private TestService testService;

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = QUEUE),exchange = @Exchange(value = EXCHANGE,autoDelete = "true"),key = RUTE_KEY)})

    public void processor(String data, @Header(AmqpHeaders.CONSUMER_QUEUE) String queue){
        try {
            //处理消息
            JSONArray jsonArray = JSONObject.parseArray(data);
            List<MsgQueueEntity> list = JSONObject.parseArray(jsonArray.toJSONString(), MsgQueueEntity.class);
            int i = testService.insertTestBath(list);
            System.out.println(i);
        }catch (Exception e){

        }

    }
}
