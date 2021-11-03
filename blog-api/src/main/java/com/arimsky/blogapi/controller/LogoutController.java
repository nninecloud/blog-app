package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.common.aop.LogAnnotation;
import com.arimsky.blogapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private LoginService loginService;

    /**
     * 退出
     * @param token
     * @return
     */
    @GetMapping
    @LogAnnotation(module = "登录", operation = "用户登出")
    public ResultData<Object> logout(@RequestHeader("Authorization") String token) {
        loginService.logout(token);
        return ResultData.success(null);
    }
}