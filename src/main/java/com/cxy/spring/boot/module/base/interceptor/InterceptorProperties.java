package com.cxy.spring.boot.module.base.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 拦截器配置.
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@Setter
@Getter
@Component
@ConfigurationProperties("coco.interceptor")
public class InterceptorProperties {

    private boolean auth;

}
