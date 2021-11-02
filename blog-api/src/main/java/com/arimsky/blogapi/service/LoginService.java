package com.arimsky.blogapi.service;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.vo.LoginParam;

/**
 * @ClassName: LoginService
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description
 */


public interface LoginService {

    ResultData<Object> login(LoginParam loginParam);

    void logout(String token);

    SysUser checkToken(String token);
}
