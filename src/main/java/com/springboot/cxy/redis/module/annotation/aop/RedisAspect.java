package com.springboot.cxy.redis.module.annotation.aop;

import com.springboot.cxy.redis.module.annotation.RedisCache;
import com.springboot.cxy.redis.module.annotation.Entity.RedisCacheEntity;
import com.springboot.cxy.redis.module.annotation.util.RedisClient;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Aspect
@Component
public class RedisAspect {

    @Resource
    private Environment env;

    @Resource
    private RedisClient redisClient;

    //    @Around("@annotation(RedisCache)")
    @Around("@annotation(redisCache)")
    public Object process(ProceedingJoinPoint pjp, RedisCache redisCache) throws Throwable {
        RedisCacheEntity cacheEntity = new RedisCacheEntity(redisCache, env, pjp);
        String key = cacheEntity.getKey();
        String value = redisClient.get(key);
        if (!StringUtils.isEmpty(value)) {
            return value;
        }
        Object proceed = pjp.proceed();//方法返回值
        redisClient.set(key, proceed.toString());
        if (!StringUtils.isEmpty(redisCache.expire())){
            Long expireTime = cacheEntity.getExpireTime();//过期时间
            TimeUnit timeUnit = redisCache.timeUnit();//时间单位
            redisClient.setExpire(key, expireTime, timeUnit);
        }
        return proceed;
    }
}
