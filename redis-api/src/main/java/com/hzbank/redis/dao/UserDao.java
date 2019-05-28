package com.hzbank.redis.dao;

import com.hzbank.redis.entity.User;

import java.util.List;

public interface UserDao {

    User selectUserById(Integer id);

    List<User> selectUser();
}
