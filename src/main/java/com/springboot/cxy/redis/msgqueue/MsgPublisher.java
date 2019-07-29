package com.springboot.cxy.redis.msgqueue;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : xy.chen
 * @time : 2019/7/24
 * @desc : 消息生产者
 */
@Component
public class MsgPublisher {
    @Resource
    private ApplicationEventPublisher publisher;

    /**
     * 发送消息
     *
     * @param msgQueueEntity
     */
    public void push(MsgQueueEntity msgQueueEntity){
        publisher.publishEvent(msgQueueEntity);
    }
}
