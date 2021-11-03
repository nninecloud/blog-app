package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.service.RegisterService;
import com.arimsky.blogapi.vo.params.LoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: RegisterController
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description 注册
 */

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    /**
     * 注册
     */
    @PostMapping
    public ResultData<Object> register(@RequestBody LoginParam loginParam){
//        System.out.println(loginParam);
        return registerService.register(loginParam);
    }
}
