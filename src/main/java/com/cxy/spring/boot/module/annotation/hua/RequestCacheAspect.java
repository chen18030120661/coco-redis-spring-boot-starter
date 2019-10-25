package com.cxy.spring.boot.module.annotation.hua;

import com.alibaba.fastjson.JSON;
import com.cxy.spring.boot.module.annotation.cong.util.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Configuration
public class RequestCacheAspect {

	private static final String CACHE_NAME = "RequestCache_";

	@Autowired
	private RedisClient redisClient;

	@Pointcut("@annotation(com.cxy.spring.boot.module.annotation.hua.RequestCache)")
	public void ServiceAspect() {

	}

	@Around("ServiceAspect()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		String cacheKey = getCacheKey(joinPoint);
		String value = redisClient.get(CACHE_NAME+cacheKey);
		if (StringUtils.isNotEmpty(value)) {
			log.info("cache hit，key [{}]", cacheKey);
			Object object = JSON.parseObject(value,Object.class);
			return object;
		} else {
			log.info("cache miss，key [{}]", cacheKey);
			Object result = joinPoint.proceed(joinPoint.getArgs());//joinPoint.proceed();
			if (result == null) {
				log.error("fail to get data from source，key [{}]", cacheKey);
			} else {
				MethodSignature signature = (MethodSignature) joinPoint.getSignature();
				Method method = signature.getMethod();
				RequestCache requestCache = method.getAnnotation(RequestCache.class);
				redisClient.set(CACHE_NAME + cacheKey, JSON.toJSONString(result) + "", requestCache.expire());
			}
			return result;
		}
	}

	private String getCacheKey(ProceedingJoinPoint joinPoint) {
		return String.format("%s.%s",
				joinPoint.getSignature().toString().split("\\s")[1], StringUtils.join(joinPoint.getArgs(), ","));

	}

}
