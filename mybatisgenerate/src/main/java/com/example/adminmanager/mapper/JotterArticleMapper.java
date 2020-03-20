package com.example.adminmanager.mapper;

import com.example.adminmanager.pojo.JotterArticle;
import com.example.adminmanager.pojo.JotterArticleWithBLOBs;

public interface JotterArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JotterArticleWithBLOBs record);

    int insertSelective(JotterArticleWithBLOBs record);

    JotterArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JotterArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(JotterArticleWithBLOBs record);

    int updateByPrimaryKey(JotterArticle record);
}