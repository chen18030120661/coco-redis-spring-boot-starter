package com.cxy.spring.boot.test.redis;

import com.cxy.spring.boot.module.annotation.RedisCache;
import com.cxy.spring.boot.module.msgqueue.entity.MsgQueueEntity;
import com.cxy.spring.boot.test.redis.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    @RedisCache(expire = "60")
    public String test(String str, int m) {
        return "测试啊啊啊";
    }

    @RedisCache(cacheKey = "#user.name", expire = "60")
    public String testUser(User user) {
        return String.valueOf(user.getAge());
    }

    public int insertTestBath(List<MsgQueueEntity> list){
        return testMapper.insertTestBath(list);
    }

}
