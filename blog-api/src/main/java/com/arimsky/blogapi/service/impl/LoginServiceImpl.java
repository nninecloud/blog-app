package com.arimsky.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.arimsky.blogapi.base.BaseValue;
import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.service.LoginService;
import com.arimsky.blogapi.service.SysUserService;
import com.arimsky.blogapi.utils.JWTUtils;
import com.arimsky.blogapi.vo.params.LoginParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LoginServiceImpl
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private static final String salt = BaseValue.SALT.getValue();

    @Resource
    private SysUserService sysUserService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

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

    @Override
    public SysUser checkToken(String token) {
        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null) {
            return null;
        }
        // 通过token 拿出 sysUser Tojosn 后 的信息
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);

        if (!StringUtils.hasText(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        log.info("=================checkToken start===========================");
        log.info(sysUser.toString());
        log.info("=================checkToken end===========================");
        return sysUser;
    }
}
