package com.arimsky.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.service.LoginService;
import com.arimsky.blogapi.service.SysUserService;
import com.arimsky.blogapi.utils.JWTUtils;
import com.arimsky.blogapi.vo.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LoginServiceImpl
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description
 */

@Service
public class LoginServiceImpl implements LoginService {

    private static final String salt = "ariBlog!@#";

    @Resource
    private SysUserService sysUserService;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ResultData<Object> login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();

        if (!StringUtils.hasText(account) || !StringUtils.hasText(password)) {
            return ResultData.error(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMessage());
        }
        // 密码 加盐后md5 加密
        String pwd = DigestUtils.md5Hex(password + salt);
        SysUser sysUser = sysUserService.findUserByAccountPwd(account, pwd);

        if (sysUser == null) {
            return ResultData.error(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMessage());
        }

        // 登陆成功, 生成token ,返回token 和 redis 中
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);

        return ResultData.success(token);

    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
    }
}
