package com.cxy.spring.boot.module.base.format.encrypt;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * 加密开关
 * .
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RequestBodyEncryptHandler.class, ResponseBodyEncryptHandler.class})
public @interface EnableEncryptApi {
}
