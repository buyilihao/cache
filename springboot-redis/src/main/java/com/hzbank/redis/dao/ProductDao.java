package com.hzbank.redis.dao;

import com.hzbank.redis.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductDao {

    @Select(value = "select * from t_product where id=#{id}")
    Product selectOneById(Integer id);

    @Update(value = "update t_product set count=count-1 where count>0 and id=#{id}")
    int updateCountById(Integer id);
}
