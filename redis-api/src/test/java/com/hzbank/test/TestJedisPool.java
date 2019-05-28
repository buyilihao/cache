package com.hzbank.test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TestJedisPool {

    private static JedisPool pool;

    private Jedis jedis;

    @Before
    public void init(){
        //构建jedisConfig类
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(3);//最大空闲连接数
        config.setMaxTotal(10);//最大连接数
        config.setMaxWaitMillis(10000);//最大等待时间，-1代表阻塞
        config.setTestOnBorrow(true);//对拿到的链接进行validateObject校验
        pool = new JedisPool(config,"192.168.196.128",6379,100000,null);
        System.out.println("jedis连接成功");
    }

    @Test
    public void testString() {
        jedis = pool.getResource();
        Set<String> keys = jedis.keys("*");//获取所有的key
        keys.forEach(new Consumer<String>() {
            public void accept(String s) {
                System.out.println("key："+s+" value："+jedis.get(s));
            }
        });
        jedis.flushAll();//清空所有的key
        jedis.set("hello", "world");
        System.out.println("key:hello value："+jedis.get("hello"));
    }

    /**
     * 任务结束释放连接到连接池
     */
    @After
    public void close(){
        if (null != jedis) {
            jedis.close();
            System.out.println("jedis连接池关闭");
        }

    }
}
