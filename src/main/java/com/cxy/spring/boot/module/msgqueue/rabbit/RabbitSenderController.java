package com.cxy.spring.boot.module.msgqueue.rabbit;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 1.基本消息模型：生产者–>队列–>一个消费者
 * 2.work消息模型：生产者–>队列–>多个消费者共同消费
 * 3.订阅模型-Fanout：广播，将消息交给所有绑定到交换机的队列，每个消费者都可以收到同一条消息
 * 4.订阅模型-Direct：定向，把消息交给符合指定 rotingKey 的队列（路由模式）
 * 5.订阅模型-Topic：通配符，把消息交给符合routing pattern（主题模式） 的队列
 * （3、4、5这三种都属于订阅模型，只不过进行路由的方式不同。)
 *
 * 地址（注解方式实现）：https://blog.csdn.net/wz6178/article/details/92843468
 */
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
//            rabbitTemplate.convertAndSend(fanoutExchange.getName(),"",content);
//            rabbitTemplate.convertAndSend("ordinaryQueue",content);
            rabbitTemplate.convertAndSend("spring.direct.exchange", "direct", content);
        }
//        rabbitTemplate.convertAndSend("topicExchange", "topic.msg", "111");
//        rabbitTemplate.convertAndSend("topicExchange", "topic.good.msg", "222");
//        rabbitTemplate.convertAndSend("topicExchange", "topic.m.z", "333");
    }
}
