package com.springboot.cxy.redis.mq;

import com.alibaba.fastjson.JSON;
import com.springboot.cxy.redis.mapper.TestMapper;
import com.springboot.cxy.redis.test.redis.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : xy.chen
 * @time : 2019/7/24
 * @desc : 消息消费者
 */
@Slf4j
@Component
public class MsgConsumer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private TestService testService;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void saveMsg() {
        try {
            List<MsgQueueEntity> list = new ArrayList<>();
            while (true){
                MsgQueueEntity msgQueueEntity = JSON.parseObject(stringRedisTemplate.opsForList().rightPop("QUEUE_SCHEDULED_CXY"), MsgQueueEntity.class);
                if (msgQueueEntity == null) {
                    break;
                }
                list.add(msgQueueEntity);
            }
            testService.insertTestBath(list);
        }catch (Exception e){
          log.error("消息队列消费异常，【{}】", e.getMessage());
        }finally {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                log.error("线程睡眠异常，【{}】", e.getMessage());
            }
        }
    }
}
