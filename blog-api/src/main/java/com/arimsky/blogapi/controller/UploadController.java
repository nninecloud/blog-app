package com.arimsky.blogapi.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.base.exception.UploadException;
import com.arimsky.blogapi.utils.UploadGiteeImgBed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("upload")
public class UploadController {

    @PostMapping
    public ResultData<Object> upload(@RequestPart("image") MultipartFile multipartFile) throws IOException {

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
            return ResultData.error(ErrorCode.IMAGE_UPLOAD_ERROR.getCode(), ErrorCode.IMAGE_UPLOAD_ERROR.getMessage());
        }
        //请求成功：返回下载地址
        JSONObject content = JSONUtil.parseObj(jsonObj.getObj("content"));

        log.info("响应data为："+ content.getObj("download_url"));

        return  ResultData.success(content.getObj("download_url"));

    }
}