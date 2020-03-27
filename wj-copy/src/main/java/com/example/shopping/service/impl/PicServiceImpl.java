package com.example.shopping.service.impl;

import com.example.base.utils.data.DateUtils;
import com.example.shopping.mapper.PictureInfoMapper;
import com.example.shopping.mypojo.PictureInfo;
import com.example.shopping.service.PicService;
import com.example.shopping.util.FtpUtil;
import com.example.shopping.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class PicServiceImpl implements PicService {
    @Autowired
    private PictureInfoMapper pictureInfoMapper;
    @Value("${ftpclient.host}")
    private String host;
    @Value("${ftpclient.port}")
    private int port;
    @Value("${ftpclient.port}")
    private int ftpPort;
    @Value("${ftpclient.username}")
    private String username;
    @Value("${ftpclient.password}")
    private String password;
    @Value("${ftpclient.basepath}")
    private String basePath;
    @Value("${ftpclient.filepath}")
    private String filePath;



    //开启事务：读写事务（可以避免脏读），propagation事务的传播行为：支持当前事务，如果当前没有事务，就新建一个事务
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, String> upload(MultipartFile file,Integer type) throws IOException {
        String genImageName = IDUtils.genImageName()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, genImageName, file.getInputStream());
        Map<String,String> map = new HashMap<>();
        String strUrl =  "http://"+ host + ftpPort + "/" + genImageName;
//        创建图片记录
        int insert = pictureInfoMapper.insert(new PictureInfo(strUrl,DateUtils.getCurrDateTime(),type));
        if(result && insert > 0){
            map.put("error", "0");
            map.put("url",strUrl);
        }else{
            map.put("error", "1");
            map.put("message", "图片上传失败");
        }
        return map;
    }
}
