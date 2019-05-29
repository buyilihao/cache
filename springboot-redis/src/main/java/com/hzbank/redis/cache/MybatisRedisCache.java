package com.hzbank.redis.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class MybatisRedisCache implements Cache{

    private RedisTemplate redisTemplate;

    private final String id; // cache instance id
    private static final long EXPIRE_TIME_IN_MINUTES = 10; // redis过期时间
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public MybatisRedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
        if (redisTemplate == null) {
            this.redisTemplate=ApplicationContextHolder.getBean("redisTemplate");
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        redisTemplate.opsForValue().set(key,value,EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        log.debug("Put query result to redis");
    }

    @Override
    public Object getObject(Object key) {
        Object value = redisTemplate.opsForValue().get(key);
        log.debug("Get query result from redis "+value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        redisTemplate.delete(key);
        log.debug("Remove query result from redis "+key);
        return null;
    }

    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return null;
            }
        });
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

}
