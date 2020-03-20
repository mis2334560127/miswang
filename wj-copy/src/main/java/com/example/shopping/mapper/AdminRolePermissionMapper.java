package com.example.shopping.mapper;

import com.example.shopping.mypojo.AdminRolePermission;
import com.example.base.mybatis.DefaultMapper;

public interface AdminRolePermissionMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRolePermission record);

    int insertSelective(AdminRolePermission record);

    AdminRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminRolePermission record);

    int updateByPrimaryKey(AdminRolePermission record);
}