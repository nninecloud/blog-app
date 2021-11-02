package com.arimsky.blogapi.service;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.SysUser;

/**
 * (SysUser)表服务接口
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public interface SysUserService {

    SysUser findSysUserById(Long authorId);

    SysUser findUserByAccountPwd(String account, String pwd);

    ResultData<Object> getUserInfoByToken(String token);

    SysUser findUserByAccount(String account);

    void save(SysUser sysUser);
}