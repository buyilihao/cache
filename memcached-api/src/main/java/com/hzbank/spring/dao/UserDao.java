package com.hzbank.spring.dao;

import com.hzbank.spring.entity.User;

public interface UserDao {

    User selectUserById(Integer id);
}
