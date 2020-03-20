package com.example.shopping.mapper;

import com.example.base.mybatis.DefaultMapper;
import com.example.shopping.mypojo.AdminRole;

public interface AdminRoleMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}