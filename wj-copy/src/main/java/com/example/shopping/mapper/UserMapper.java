package com.example.shopping.mapper;

import com.example.shopping.mypojo.User;
import com.example.base.mybatis.DefaultMapper;

public interface UserMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}