package com.hzbank.demo;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author LeeHao
 * @Date 2019/5/22 08:48
 * @Description：
 */
public class Demo {
    public static final Logger logger= LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("cache:11211"));
        // 多个Memcached Server：host1:port1host2:port2

        try {
            MemcachedClient memcachedClient = builder.build();
            // 存储数据 参数⼀一：key名 参数⼆二：expire时间（单位秒）表示永久存储（默认是⼀一个月）参数三：value值
            memcachedClient.set("hello", 1000, "Hello,xmemcached");
            // 获取数据
            String value = memcachedClient.get("hello");
            logger.info("删除前：key：hello--> value：" + value);
            // 删除数据
            memcachedClient.delete("hello");
            value = memcachedClient.get("hello");
            logger.info("删除后：key：hello--> value：" + value);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

    }
}
