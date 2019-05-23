package com.hzbank.test;

import com.hzbank.MemcachedApplication;
import net.rubyeye.xmemcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemcachedApplication.class)
public class TestMemcached {

    @Autowired
    private MemcachedClient client;

    @Test
    public void test() {
        try {
            // 设置/获取
            client.set("cache", 36000, "set/get");
            System.out.println("get结果："+client.get("cache"));
            // 替换
            client.replace("cache", 36000, "replace");
            System.out.println("replace后："+client.get("cache"));
            // 移除
            client.delete("cache");
            System.out.println("delete后："+client.get("cache"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
