package com.cxy.spring.boot.module.annotation;

import com.cxy.spring.boot.module.annotation.aop.RedisAspect;
import com.cxy.spring.boot.module.annotation.entity.RedisCacheEntity;
import com.cxy.spring.boot.module.annotation.util.RedisClient;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RedisAspect.class, RedisCacheEntity.class, RedisClient.class})
public @interface EnableRedisCache {

}

