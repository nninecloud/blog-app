package com.arimsky.blogapi.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.arimsky.blogapi.base.ErrorCode;

import org.apache.http.entity.ContentType;
import com.arimsky.blogapi.base.exception.UploadException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName: UploadGiteeImgBedTest
 * @Author: aRimsiky
 * @Date: 2021/11/04
 * @Description
 */

@SpringBootTest
@Slf4j
class UploadGiteeImgBedTest {

    @Test
    void createUploadFileUrl() throws IOException {
        String filePath = "src/main/resources/static/img/logo.b3a48c0.png";
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        // MockMultipartFile(String name, @Nullable String originalFilename, @Nullable String contentType, InputStream contentStream)
        // 其中originalFilename,String contentType 旧名字，类型  可为空
        // ContentType.APPLICATION_OCTET_STREAM.toString() 需要使用HttpClient的包
        MultipartFile multipartFile = new MockMultipartFile("copy"+file.getName(),file.getName(),ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);

        log.info("uploadImg()请求已来临...");
        //根据文件名生成指定的请求url
        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null){
            throw new UploadException(ErrorCode.IMAGE_EXIST_ERROR.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMessage());
        }
        String targetURL = UploadGiteeImgBed.createUploadFileUrl(originalFilename);
        log.info("目标url："+targetURL);
        //请求体封装
        Map<String, Object> uploadBodyMap = UploadGiteeImgBed.getUploadBodyMap(multipartFile.getBytes());
        //借助HttpUtil工具类发送POST请求
        String JSONResult = HttpUtil.post(targetURL, uploadBodyMap);
        //解析响应JSON字符串
        JSONObject jsonObj = JSONUtil.parseObj(JSONResult);
        //请求失败
        if(jsonObj == null || jsonObj.getObj("commit") == null){
            System.out.println("请求失败");
//            return ResultData.error(ErrorCode.IMAGE_UPLOAD_ERROR.getCode(), ErrorCode.IMAGE_UPLOAD_ERROR.getMessage());
        }
        //请求成功：返回下载地址
        JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));

        log.info("响应data为："+ content.getObj("download_url"));

    }

    @Test
    void getUploadBodyMap() {
    }
}