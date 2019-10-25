package com.cxy.spring.boot.module.annotation.cong.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 因为我们在做开发的时候，并不是说在每一个地方都能将属性注入到我们想要的地方去的，比如在Utils使用到dao，
 * 我们就不能直接注入了，这个时候就是我们需要封装springContext的时候了，而ApplicationContextAware就起了关键性的作用。
 *
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 */
@Slf4j
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        log.info("Initializing SpringCacheHolder");
        Assert.notNull(applicationContext, "SpringCacheHolder Load Error");
        context = applicationContext; // NOSONAR
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) context.getBean(name); // NOSONAR
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

}
