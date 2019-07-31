package com.springboot.cxy.redis.module.quartzjob.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})     
@Retention(RetentionPolicy.RUNTIME)     
@Documented
public @interface AddSysLog {
	String desc() default "无描述信息";
}
