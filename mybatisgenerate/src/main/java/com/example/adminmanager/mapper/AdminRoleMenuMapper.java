package com.example.adminmanager.mapper;

import com.example.adminmanager.pojo.AdminRoleMenu;

public interface AdminRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRoleMenu record);

    int insertSelective(AdminRoleMenu record);

    AdminRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRoleMenu record);

    int updateByPrimaryKey(AdminRoleMenu record);
}