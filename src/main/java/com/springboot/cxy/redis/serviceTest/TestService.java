package com.springboot.cxy.redis.serviceTest;

import com.springboot.cxy.redis.annotation.RedisCache;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @RedisCache(expire = "60")
    public String test(String str,int m){
        return "测试啊啊啊";
    }
}
