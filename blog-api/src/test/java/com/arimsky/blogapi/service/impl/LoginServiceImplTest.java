package com.arimsky.blogapi.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: LoginServiceImplTest
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description
 */

@SpringBootTest
class LoginServiceImplTest {
    private static final String salt = "ariBlog!@#";

    @Test
    public void Md5Test() throws Exception{
        String password = "111111";
        String pwd = DigestUtils.md5Hex(password + salt);

        System.out.println(pwd);
    }
}