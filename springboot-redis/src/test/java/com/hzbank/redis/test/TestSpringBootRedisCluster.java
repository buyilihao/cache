package com.hzbank.redis.test;

import com.hzbank.redis.SpringbootRedis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedis.class)
public class TestSpringBootRedisCluster {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testHashPut() {
        Map<String, String> map = new HashMap<>();
        map.put("河南", "郑州");
        map.put("浙江", "杭州");
        map.put("江苏", "南京");
        redisTemplate.opsForHash().putAll("hash",map);
    }

    @Test
    public void testHashGet() {
        Object o = redisTemplate.opsForHash().get("hash", "河南");
        System.out.println("河南-->"+o);
        Object o2 = redisTemplate.opsForHash().get("hash", "浙江");
        System.out.println("浙江-->"+o2);
    }
}
