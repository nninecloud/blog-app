package com.arimsky.blogapi.service;

import com.arimsky.blogapi.pojo.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName: SysUserServiceTest
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */

@SpringBootTest
class SysUserServiceTest {

    @Resource
    private SysUserService userService;

    @Test
    void findSysUserById() {
        SysUser sysUserById = userService.findSysUserById(1L);
        System.out.println(sysUserById);
    }

    @Test
    void findUserByAccountPwd() {
    }

    @Test
    void getUserInfoByToken() {
    }

    @Test
    void findUserByAccount() {
    }
}