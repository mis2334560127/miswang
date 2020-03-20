package com.example.shopping.mapper;

import com.example.shopping.mypojo.JotterArticle;
import com.example.base.mybatis.DefaultMapper;

public interface JotterArticleMapper  extends DefaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JotterArticle record);

    int insertSelective(JotterArticle record);

    JotterArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JotterArticle record);

    int updateByPrimaryKeyWithBLOBs(JotterArticle record);

    int updateByPrimaryKey(JotterArticle record);
}