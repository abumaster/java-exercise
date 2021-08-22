package com.abumaster.example.springworkredis.common;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis的配置，读取配置文件，创建连接池
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/18
 */
@Component
@ConfigurationProperties(prefix = "redis")
@PropertySource(value = "classpath:redis.properties")
public class RedisConfig {
    private static String host;
    private static Integer port;
    private static String auth;
    private static Integer timeout;

    private static class innerClass {
        private static final JedisPool jedisPool = new JedisPool(jedisPoolConfig(),
                host,port,timeout
                );

        private static final RedissonClient redissonClient=Redisson.create(config());

        private static JedisPoolConfig jedisPoolConfig() {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(10);
            config.setMaxTotal(50);
            config.setMaxWaitMillis(5000);
            return config;
        }

        private static Config config() {
            Config config = new Config();
            config.useSingleServer().setAddress(host+":"+port).setDatabase(0)
                    .setConnectionMinimumIdleSize(10).setConnectionPoolSize(50);
            return config;
        }
    }
    public static JedisPool getJedisPool() {
        return innerClass.jedisPool;
    }

    public static RedissonClient redissonClient() {
        return innerClass.redissonClient;
    }
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        RedisConfig.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        RedisConfig.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        RedisConfig.auth = auth;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        RedisConfig.timeout = timeout;
    }
}
