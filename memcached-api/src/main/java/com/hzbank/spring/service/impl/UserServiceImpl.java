package com.hzbank.spring.service.impl;

import com.hzbank.spring.dao.UserDao;
import com.hzbank.spring.entity.User;
import com.hzbank.spring.service.UserService;
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
