package com.example.adminmanager.mapper;

import com.example.adminmanager.pojo.AdminRolePermission;

public interface AdminRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRolePermission record);

    int insertSelective(AdminRolePermission record);

    AdminRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRolePermission record);

    int updateByPrimaryKey(AdminRolePermission record);
}