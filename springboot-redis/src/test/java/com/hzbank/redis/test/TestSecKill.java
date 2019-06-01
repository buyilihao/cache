package com.hzbank.redis.test;

import com.hzbank.redis.SpringbootRedis;
import com.hzbank.redis.seckill.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedis.class)
public class TestSecKill {

    @Autowired
    private SecKillService secKillService;

    @Test
    public void loadConut(){
        secKillService.loadCount(2);
    }

    @Test
    public void secKill(){
        for (int i = 0; i < 200; i++) {
            secKillService.sendMsg(2,i);
        }

    }
}
