package com.springboot.cxy.redis.domain;

import com.alibaba.fastjson.JSON;
import com.springboot.cxy.redis.annotation.RedisCache;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Slf4j
@Data
public class RedisCacheEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private RedisCache redisCache;
    private Environment env;
    private ProceedingJoinPoint pjp;
    private String key;
    private Long expireTime;

    public RedisCacheEntity(RedisCache redisCache, Environment env, ProceedingJoinPoint pjp) {
        this.redisCache = redisCache;
        this.env = env;
        this.pjp = pjp;
        this.initProperties();
    }

    public void initProperties() {
        this.initKey();
        this.initExpireTime();
    }

    public void initKey() {
        //key目录
        String cacheDir = redisCache.cacheDir();
        if (StringUtils.isEmpty(cacheDir)) {
            String className = pjp.getTarget().getClass().getSimpleName();//类名
            String methodName  = pjp.getSignature().getName();//方法名
            cacheDir = className + ":" + methodName;
        }
        //key键
        String cacheKey = redisCache.cacheKey();
        if (StringUtils.isEmpty(cacheKey)) {
            cacheKey = JSON.toJSONString(pjp.getArgs());//方法参数
        } else if (cacheKey.startsWith("#")) {
            //TODO SPEL解析
        }
        this.key = cacheDir + ":" + cacheKey;
    }

    public void initExpireTime() {
        String expire = redisCache.expire();
        String timeRegex = "^\\d+$";//正则表达式（校验是否是数字）
        if (!StringUtils.isEmpty(expire)) {
            boolean flag = expire.matches(timeRegex);
            if (flag) {
                this.expireTime = Long.valueOf(expire);
            } else {
                String property = env.getProperty(expire);
                if (property != null && property.matches(timeRegex)) {
                    this.expireTime = Long.valueOf(property);
                }
            }
        }
    }

    // MethodSignature signature = (MethodSignature) pjp.getSignature();
    //        Method method = signature.getMethod();
    //        RedisCache redisCache = method.getAnnotation(RedisCache.class);
}
