package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.utils.UserThreadLocal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("user")
    public ResultData test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return ResultData.success(null);
    }
}
