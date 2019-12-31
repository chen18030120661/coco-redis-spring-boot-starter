package com.cxy.spring.boot.module.msgqueue.rabbit.fanout;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 冯战魁
 * @Date 2018/1/12 下午2:50
 */
@Configuration
public class RabbitConfig {
//    @Bean(name="aMessage")
//    public Queue AMessage() {
//        return new Queue("fanout.A");
//    }
//    @Bean(name="bMessage")
//    public Queue BMessage() {
//        return new Queue("fanout.B");
//    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");//配置广播路由器
    }
//    @Bean
//    Binding bindingExchangeA(@Qualifier("aMessage") Queue aMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(aMessage).to(fanoutExchange);
//    }

//    @Bean
//    Binding bindingExchangeB(@Qualifier("bMessage") Queue bMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(bMessage).to(fanoutExchange);
//    }

    @Bean
    public Binding binding0(FanoutExchange fanoutExchange, Queue systemQueue) {
        return BindingBuilder.bind(systemQueue).to(fanoutExchange);
    }
    @Bean
    public Queue systemQueue() {
        //AnonymousQueue将创建一个带有随机名字的队列
        return new AnonymousQueue();
    }
}
