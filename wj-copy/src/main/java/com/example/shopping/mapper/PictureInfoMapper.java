package com.example.shopping.mapper;

import com.example.base.mybatis.DefaultMapper;
import com.example.shopping.mypojo.PictureInfo;

public interface PictureInfoMapper  extends DefaultMapper {
    int insert(PictureInfo pictureInfo);
}
