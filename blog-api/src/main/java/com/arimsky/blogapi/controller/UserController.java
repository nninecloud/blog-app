package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: UserController
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description 用户接口
 */

@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 获取当前用户信息
     * @param token
     * @return
     */
    @GetMapping("/currentUser")
    public ResultData<Object> currentUser(@RequestHeader("Authorization") String token){

        return sysUserService.getUserInfoByToken(token);
    }
}
