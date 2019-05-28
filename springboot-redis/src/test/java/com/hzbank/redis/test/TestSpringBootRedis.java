package com.hzbank.redis.test;

import com.hzbank.redis.SpringbootRedis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedis.class)
public class TestSpringBootRedis {

    @Autowired
    private RedisTemplate redisTemplate;//不要加泛型，否则不能自动注入

    @Test
    public void testListSet() {
        List<String> name = new ArrayList<>();
        name.add("LH");
        name.add("XKA");
        name.add("FHJ");
        name.add("DCT");
        name.add("PH");
        name.add("XYZ");
        redisTemplate.opsForList().leftPushAll("list",name);
    }

    @Test
    public void testListGet() {
        //从list中pop后，数据不再存在
        Object leftPop = redisTemplate.opsForList().leftPop("list");
        System.out.println("leftPop: "+leftPop);
        Object rightPop = redisTemplate.opsForList().rightPop("list");
        System.out.println("rightPop: "+rightPop);
    }
}
