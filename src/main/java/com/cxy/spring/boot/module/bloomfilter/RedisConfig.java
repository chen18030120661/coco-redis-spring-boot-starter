package com.cxy.spring.boot.module.bloomfilter;

import io.rebloom.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import javax.annotation.Resource;

/**
 * @author xujinbang
 * @date 2019/11/8.
 */
@Configuration
public class RedisConfig {

    @Resource
    private Environment environment;

    @Bean(name = "defaultPool")
    public JedisPool defaultPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.max-idle")));
        jedisPoolConfig.setMinIdle(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.min-idle")));
        jedisPoolConfig.setMaxTotal(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.max-active")));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.max-wait")));
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, environment.getProperty("spring.redis.host"),
                Integer.valueOf(environment.getProperty("spring.redis.port")), Integer.valueOf(environment.getProperty("spring.redis.timeout")),environment.getProperty("spring.redis.password"),Integer.valueOf(environment.getProperty("spring.redis.database")));
        return jedisPool;
    }
    @Bean(name = "recDB9Pool")
    public JedisPool recDB9Pool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.max-idle")));
        jedisPoolConfig.setMinIdle(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.min-idle")));
        jedisPoolConfig.setMaxTotal(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.max-active")));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(environment.getProperty("spring.redis.jedis.pool.max-wait")));
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, environment.getProperty("spring.redis.bloom.host"),
                Integer.valueOf(environment.getProperty("spring.redis.bloom.port")), Integer.valueOf(environment.getProperty("spring.redis.timeout")),environment.getProperty("spring.redis.bloom.password"), Integer.valueOf(environment.getProperty("spring.redis.bloom.database")));
        return jedisPool;
    }

    @Bean(name = "redisBloomClient")
    public Client dbClient(){
        return new Client(recDB9Pool());
    }
}
