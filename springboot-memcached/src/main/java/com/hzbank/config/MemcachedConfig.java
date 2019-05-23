package com.hzbank.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MemcachedConfig {

    @Autowired
    private MemcachedProperties properties;

    //创建MemcachedClientBuilder，和spring配置文件类型
    @Bean
    public MemcachedClientBuilder memcachedClientBuilder(){
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(properties.getServers());
        //设置连接池个数
        builder.setConnectionPoolSize(properties.getPoolSize());
        //设置失败模式
        builder.setFailureMode(false);
        builder.setSanitizeKeys(properties.isSanitizeKeys());
        builder.setCommandFactory(new BinaryCommandFactory());
        builder.setSessionLocator(new KetamaMemcachedSessionLocator());
        builder.setOpTimeout(3000);

        return builder;
    }

    @Bean
    public MemcachedClient memcachedClient(MemcachedClientBuilder builder){
        MemcachedClient client= null;
        try {
            client = builder.build();
//            client.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

}
