package com.example.shopping.mapper;

import com.example.base.mybatis.DefaultMapper;
import com.example.shopping.mypojo.Category;

public interface CategoryMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}