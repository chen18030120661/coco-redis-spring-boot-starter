package com.cxy.spring.boot.module.annotation.cong;


import com.cxy.spring.boot.module.annotation.cong.aop.RedisAspect;
import com.cxy.spring.boot.module.annotation.cong.entity.RedisCacheEntity;
import com.cxy.spring.boot.module.annotation.cong.util.RedisClient;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RedisAspect.class, RedisCacheEntity.class, RedisClient.class})
public @interface EnableRedisCache {

}

