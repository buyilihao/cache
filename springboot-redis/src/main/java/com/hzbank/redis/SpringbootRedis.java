package com.hzbank.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootRedis {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedis.class, args);
    }
}
