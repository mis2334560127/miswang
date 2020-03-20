package com.example.shopping.mapper;

import com.example.base.mybatis.DefaultMapper;
import com.example.shopping.mypojo.AdminRoleMenu;

public interface AdminRoleMenuMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRoleMenu record);

    int insertSelective(AdminRoleMenu record);

    AdminRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRoleMenu record);

    int updateByPrimaryKey(AdminRoleMenu record);
}