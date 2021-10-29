package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @ClassName: SysUserMapperTest
 * @author: aRimsiky
 * @date: 2021/10/27
 * @description
 */

@SpringBootTest
class SysUserMapperTest {

    @Resource
    private SysUserMapper sysUserMapper;


    @Test
    void queryById() {

        SysUser sysUser = sysUserMapper.selectById(1L);

        System.out.println(sysUser);
    }
}