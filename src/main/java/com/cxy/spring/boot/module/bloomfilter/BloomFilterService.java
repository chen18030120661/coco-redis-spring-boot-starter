package com.cxy.spring.boot.module.bloomfilter;

import io.rebloom.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
@Slf4j
public class BloomFilterService {
    @Autowired
    @Qualifier("redisBloomClient")
    private Client client;
    @Value("${spring.redis.bloom.init-capacity}")
    private long initCapacity;
    @Value("${spring.redis.bloom.error-rate}")
    private double errorRate;
    @Resource(name = "recDB9Pool")
    private JedisPool recDB9Pool;

    private final String bn1 = "cocoBloomHH";

    @PostConstruct
    public void  init(){
        try(Jedis con = recDB9Pool.getResource();){
            if (!con.exists(bn1)){
                client.createFilter(bn1,initCapacity,errorRate);
            }
        }catch (Exception e){
            log.error("初始化布隆过滤器异常：{}",e.getMessage());
        }
    }

    /**
     * 判断uuid在布隆器是否存在
     *
     * @param uuid
     * @return
     */
    public boolean containMulit(String uuid){
        return client.exists(bn1, uuid);
    }

    public boolean addUuid(String uuid){
        return client.add(bn1, uuid);
    }

}
