package com.hzbank.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "memcached")
public class MemcachedProperties {
    private String servers;
    private Integer poolSize;
    private boolean sanitizeKeys;
}
