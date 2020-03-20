package com.example.shopping.mypojo;

import java.util.Date;

public class PictureInfo {
    private Long id;
    private String picUrl;
    private Date createTime;
    private Integer types;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return types;
    }

    public void setType(Integer type) {
        this.types = type;
    }

    public PictureInfo(String picUrl, Date createTime, Integer types) {
        this.picUrl = picUrl;
        this.createTime = createTime;
        this.types = types;
    }

    public PictureInfo() {
    }
}
