package com.arimsky.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.arimsky.blogapi.base.BaseValue;
import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.service.RegisterService;
import com.arimsky.blogapi.service.SysUserService;
import com.arimsky.blogapi.utils.JWTUtils;
import com.arimsky.blogapi.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RegisterServiceImpl
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ResultData<Object> register(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickName = loginParam.getNickname();
        if (!StringUtils.hasText(account) || !StringUtils.hasText(password) || !StringUtils.hasText(nickName)) {
            return ResultData.error(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMessage());
        }

        SysUser user = sysUserService.findUserByAccount(account);
        if (user != null) {
            return ResultData.error(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMessage());
        }

        SysUser sysUser = getSysUser(account, password, nickName);

        sysUserService.save(sysUser);

        String token = JWTUtils.createToken(sysUser.getId());


        //路由出问题，注册后没实现自动登录，不存token了
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser) , 1, TimeUnit.DAYS);


        return ResultData.success(token);
    }

    private SysUser getSysUser(String account, String password, String nickName) {
        SysUser sysUser = new SysUser();
        sysUser.setAccount(account);
        sysUser.setNickname(nickName);
        sysUser.setPassword(DigestUtils.md5Hex(password + BaseValue.SALT.getValue()));
        sysUser.setAdmin(1);
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setDeleted(0);
        sysUser.setEmail("");
        sysUser.setMobilePhoneNumber("");
        sysUser.setSalt("");
        sysUser.setStatus("");
        return sysUser;
    }
}
