package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.common.aop.LogAnnotation;
import com.arimsky.blogapi.service.LoginService;
import com.arimsky.blogapi.vo.params.LoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: LoginController
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description 登录controller
 */

@RestController
@RequestMapping("login")
public class LoginController {


    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @return 登录成功后返回一个token
     */
    @PostMapping
    @LogAnnotation(module = "登录", operation = "用户登录")
    public ResultData<Object> login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);

    }


}
