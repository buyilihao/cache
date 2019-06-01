package com.hzbank.redis.service.impl;

import com.hzbank.redis.dao.ProductDao;
import com.hzbank.redis.entity.Product;
import com.hzbank.redis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findOne(Integer id) {
        return productDao.selectOneById(id);
    }

    @Override
    public int decreCount(Integer id) {
        return productDao.updateCountById(id);
    }
}
