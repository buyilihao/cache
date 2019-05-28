package com.hzbank.test;

import com.hzbank.redis.entity.User;
import com.hzbank.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class TestSpringRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void testObject() {
        User user = userService.selectUserById(1);
        Set<String> keys = redisTemplate.keys("*");//获取所有的key
        redisTemplate.delete(keys);//删除所有的key
        redisTemplate.opsForValue().set("standalone",user);
        redisTemplate.opsForValue().set("hello","world");
        System.out.println(" value:"+redisTemplate.opsForValue().get("standalone"));
        System.out.println(" value:"+redisTemplate.opsForValue().get("hello"));

    }
}
