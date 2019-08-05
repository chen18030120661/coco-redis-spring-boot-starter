package com.cxy.spring.boot.module.base.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description : IP工具类
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IpUtils {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
    }
}
