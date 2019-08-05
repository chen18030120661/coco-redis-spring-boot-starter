package com.cxy.spring.boot.module.base.format.encrypt;

import java.lang.annotation.*;

/**
 * 接口加密注解.
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {
}
