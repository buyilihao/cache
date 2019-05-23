package com.hzbank.test;

import net.rubyeye.xmemcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class TestXMemcached {

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void test() {
        try {
            // 设置/获取
            memcachedClient.set("zlex", 36000, "set/get");
            System.out.println(memcachedClient.get("zlex"));
            // 替换
            memcachedClient.replace("zlex", 36000, "replace");
            System.out.println(memcachedClient.get("zlex"));
            // 移除
            memcachedClient.delete("zlex");
            System.out.println(memcachedClient.get("zlex"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
