package com.springboot.cxy.redis.test.redis;

import com.springboot.cxy.redis.module.annotation.RedisCache;
import com.springboot.cxy.redis.module.msgqueue.entity.MsgQueueEntity;
import com.springboot.cxy.redis.test.redis.mapper.TestMapper;
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
