package com.example.shopping.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface PicService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    Map<String,String> upload(MultipartFile file,Integer type) throws IOException;
}
