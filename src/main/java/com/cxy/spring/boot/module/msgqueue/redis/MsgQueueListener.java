package com.cxy.spring.boot.module.msgqueue.redis;

import com.alibaba.fastjson.JSON;
import com.cxy.spring.boot.module.msgqueue.MsgQueueEntity;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : xy.chen
 * @time : 2019/7/24
 * @desc : 消息队列监听
 */
@EnableAsync
@Component
public class MsgQueueListener {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Async
    @EventListener(MsgQueueEntity.class)
    public void msgQueueListener(MsgQueueEntity msgQueueEntity){
        stringRedisTemplate.opsForList().leftPush("QUEUE_SCHEDULED_CXY", JSON.toJSONString(msgQueueEntity));
    }

}
