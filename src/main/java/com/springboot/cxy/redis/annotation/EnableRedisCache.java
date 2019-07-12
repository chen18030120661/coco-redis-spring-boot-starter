package com.springboot.cxy.redis.annotation;

import com.springboot.cxy.redis.aop.RedisAspect;
import com.springboot.cxy.redis.domain.RedisCacheEntity;
import com.springboot.cxy.redis.util.RedisClient;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RedisAspect.class, RedisCacheEntity.class, RedisClient.class})
public @interface EnableRedisCache {
    String value() default "";
}

