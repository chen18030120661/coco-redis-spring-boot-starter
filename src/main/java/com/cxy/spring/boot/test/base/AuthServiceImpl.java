package com.cxy.spring.boot.test.base;

import com.cxy.spring.boot.module.base.interceptor.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * .
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        final String token = request.getHeader("token");
//        if (token != null) {
            return true;
//        }
//        return false;
    }
}
