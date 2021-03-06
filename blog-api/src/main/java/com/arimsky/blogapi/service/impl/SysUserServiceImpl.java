package com.arimsky.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.dao.SysUserMapper;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.service.SysUserService;
import com.arimsky.blogapi.utils.JWTUtils;
import com.arimsky.blogapi.vo.LoginUserVo;
import com.arimsky.blogapi.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (SysUser)表服务实现类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public SysUser findSysUserById(Long authorId) {

        SysUser sysUser;
        sysUser = sysUserMapper.selectById(authorId);

        if (sysUser == null) {
            sysUser = new SysUser();
            sysUser.setNickname("管理员创建");
        }

        return sysUser;
    }


    @Override
    public SysUser findUserByAccountPwd(String account, String pwd) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(
                        SysUser::getId,
                        SysUser::getAccount,
                        SysUser::getAvatar,
                        SysUser::getNickname).
                eq(SysUser::getAccount, account).
                eq(SysUser::getPassword, pwd).
                last("limit 1");

        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public ResultData<Object> getUserInfoByToken(String token) {
        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null) {
            return ResultData.error(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMessage());
        }
        // 通过token 拿出 sysUser Tojosn 后 的信息
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        if (sysUser == null) {
            /**
             * redis 每次重启之后数据都被清空了,找不到之前的token
             */
            return ResultData.error(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMessage());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());

        return ResultData.success(loginUserVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getAccount, account);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        int insert = sysUserMapper.insert(sysUser);
        if (insert != 1) {
            System.err.println("保存对象失败 save error");
        }
    }

    @Override
    public UserVo findUserVoById(Long authorId) {

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getId, authorId);

        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        if (sysUser == null) {
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("AriBlog");
        }
        UserVo userVo = new UserVo();
        userVo.setAvatar(sysUser.getAvatar());
        userVo.setNickname(sysUser.getNickname());
        userVo.setId(sysUser.getId());

        return userVo;
    }


}