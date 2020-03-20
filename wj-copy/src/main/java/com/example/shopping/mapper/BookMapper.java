package com.example.shopping.mapper;

import com.example.base.mybatis.DefaultMapper;
import com.example.shopping.mypojo.Book;

public interface BookMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}