package com.springboot.cxy.redis.module.annotation;

import com.springboot.cxy.redis.module.annotation.aop.RedisAspect;
import com.springboot.cxy.redis.module.annotation.entity.RedisCacheEntity;
import com.springboot.cxy.redis.module.annotation.util.RedisClient;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RedisAspect.class, RedisCacheEntity.class, RedisClient.class})
public @interface EnableRedisCache {

}

