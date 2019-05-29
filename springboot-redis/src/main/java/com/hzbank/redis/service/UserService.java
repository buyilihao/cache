package com.hzbank.redis.service;

import com.hzbank.redis.entity.User;

public interface UserService {
    User selectUserById(Integer id);
}
