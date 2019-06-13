package com.hzbank.redis.test;

import com.hzbank.redis.SpringbootRedis;
import com.hzbank.redis.seckill.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedis.class)
public class TestSecKill {

    private static final int THREAMCOUNT=1000;
    //倒计数，用于并发测试
    private CountDownLatch latch = new CountDownLatch(THREAMCOUNT);

    @Autowired
    private SecKillService secKillService;

    @Test
    public void loadConut(){
        secKillService.loadCount(2);
    }

    @Test
    public void secKill() throws InterruptedException {

        for (int i = 0; i <THREAMCOUNT ; i++) {
            new Thread(new Send(i,2)).start();
            latch.countDown();//等待线程减1
        }
        //主线程挂起
        Thread.currentThread().join();
    }


    class Send implements Runnable {

        private int id;
        private int productId;

        public Send(int id, int productId) {
            this.id = id;
            this.productId = productId;
        }

        @Override
        public void run() {
            try {
                //所有子线程在这里等待，当所有子线程实例化后，同时停止等待
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            secKillService.sendMsg(2,id);
        }
    }
}


