package com.cxy.spring.boot.module.annotation.cong.entity;

import com.alibaba.fastjson.JSON;
import com.cxy.spring.boot.module.annotation.cong.RedisCache;
import com.cxy.spring.boot.module.annotation.cong.util.SpelUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;

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

    /**
     * 初始化参数
     */
    public void initProperties() {
        this.generateKey();
        this.generateExpireTime();
    }

    /**
     * 获取缓存key
     */
    public void generateKey() {
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
            //SPEL解析 AOP
            cacheKey = SpelUtil.parse(pjp, cacheKey);
        }
        this.key = cacheDir + ":" + cacheKey;
    }

    /**
     * 获取过期时间
     */
    public void generateExpireTime() {
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

    /**
     * 获取类上的注解(暂时未用到)
     */
    private void generateClassAnnotation() {
        final Class declaringType = pjp.getSignature().getDeclaringType();
        final Annotation annotation = declaringType.getAnnotation(Service.class);
        if (annotation == null) {
            return;
        }
        final Service service = (Service) annotation;
    }
}
