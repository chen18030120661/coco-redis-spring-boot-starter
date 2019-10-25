package com.cxy.spring.boot.module.annotation.hua;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发控制
 * */
@Slf4j
@Component
@Scope
@Aspect
@Order(1)
public class ServicelockAspect {

  private static Lock lock = new ReentrantLock(true);

  @Pointcut("@annotation(com.cxy.spring.boot.module.annotation.hua.ServiceLock)")
  public void lockAspect() {
  }

  @Around("lockAspect()")
  public Object around(ProceedingJoinPoint joinPoint) throws Exception {
    lock.lock();
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    ServiceLock serviceLock = method.getAnnotation(ServiceLock.class);
    Object obj = null;
    try {
      obj = joinPoint.proceed();
    } catch (Throwable e) {
        throw new Exception(serviceLock.msg());
    } finally {
        lock.unlock();
    }
    return obj;
  }
}
