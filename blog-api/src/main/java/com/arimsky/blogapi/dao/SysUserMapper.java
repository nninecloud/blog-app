package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * (SysUser)表数据库访问层
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

}