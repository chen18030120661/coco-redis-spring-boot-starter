package com.cxy.spring.boot.module.annotation.hua;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented    
public  @interface RequestCache {

	int expire() default 300;


}
