package com.hzbank.redis.service;

import com.hzbank.redis.entity.Product;

public interface ProductService {

    Product findOne(Integer id);

    int decreCount(Integer id);
}
