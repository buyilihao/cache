package com.hzbank.spring.service;

import com.hzbank.spring.entity.User;

public interface UserService {
    User selectUserById(Integer id);
}
