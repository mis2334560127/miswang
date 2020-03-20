package com.example.shopping.mapper;

import com.example.base.mybatis.DefaultMapper;
import com.example.shopping.mypojo.AdminPermission;

public interface AdminPermissionMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminPermission record);

    int insertSelective(AdminPermission record);

    AdminPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminPermission record);

    int updateByPrimaryKey(AdminPermission record);
}