package com.cxy.spring.boot.module.base.interceptor.auth;

import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权业务逻辑.
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
public interface AuthService {


    default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
