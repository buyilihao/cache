package com.hzbank.redis.dao;

import com.hzbank.redis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

//    @Select(value = "select id,name,age from t_user where id=#{id}")
    User selectUserById(Integer id);

//    @Select(value = "select id,name,age from t_user")
    List<User> selectUser();
}
