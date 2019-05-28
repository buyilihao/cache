package com.hzbank.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class TestJedisCluster {

    private static JedisCluster cluster;

    @Before
    public void init(){
        //创建集群节点信息
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.196.128", 7000));
        nodes.add(new HostAndPort("192.168.196.128", 7001));
        nodes.add(new HostAndPort("192.168.196.128", 7002));
//        nodes.add(new HostAndPort("192.168.196.128", 7003));
//        nodes.add(new HostAndPort("192.168.196.128", 7004));
//        nodes.add(new HostAndPort("192.168.196.128", 7005));
        //构建jedisConfig类
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);//最大空闲连接数
        config.setMaxTotal(20);//最大连接数
        config.setMaxWaitMillis(10000);//最大等待时间，-1代表阻塞
        config.setTestOnBorrow(true);//对拿到的链接进行validateObject校验
        //创建集群实例
        cluster = new JedisCluster(nodes,config);
    }

    @Test
    public void testCluster(){
        //获取集群节点信息
        Map<String, JedisPool> nodes = cluster.getClusterNodes();
        nodes.forEach(new BiConsumer<String, JedisPool>() {
            @Override
            public void accept(String s, JedisPool jedisPool) {
                System.out.println(s+"-->"+jedisPool.toString());

            }
        });
        cluster.set("JedisCluster", "JedisCluster");
        System.out.println(cluster.get("JedisCluster"));
    }

    @After
    public void close() {
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
