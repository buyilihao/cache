package com.hzbank.redis.test;

import com.hzbank.redis.SpringbootRedis;
import com.hzbank.redis.entity.User;
import com.hzbank.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedis.class)
public class TestUser {

    @Autowired
    private UserService userService;

    @Test
    public void findOne() {
        User user = userService.selectUserById(1);
        System.out.println("one-->"+user.toString());
        User user2 = userService.selectUserById(1);
        System.out.println("two-->"+user2.toString());
    }
}
