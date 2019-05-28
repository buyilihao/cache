package com.hzbank.redis.service.impl;

import com.hzbank.redis.dao.UserDao;
import com.hzbank.redis.entity.User;
import com.hzbank.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }
}
