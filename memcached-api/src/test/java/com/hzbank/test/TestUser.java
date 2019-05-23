package com.hzbank.test;


import com.hzbank.spring.entity.User;
import com.hzbank.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class TestUser {

    @Autowired
    private UserService userService;

    @Test
    public void findOne() throws InterruptedException {
        User user = userService.selectUserById(1);
        System.err.println("user-->"+user.toString());
        Thread.sleep(1000);
        User user2 = userService.selectUserById(1);
        System.err.println("user2-->"+user2.toString());
    }
}
