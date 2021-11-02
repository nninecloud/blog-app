package com.arimsky.blogapi.utils;

import com.arimsky.blogapi.pojo.entity.SysUser;

/**
 * @ClassName: UserThreadLocal
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description ThreadLocal保存用户信息
 */


public class UserThreadLocal {
    private UserThreadLocal(){}

    // 线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }

}
