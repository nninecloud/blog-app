package com.arimsky.blogapi.common.cache;

import java.lang.annotation.*;

/**
 * @ClassName: Cache
 * @Author: aRimsiky
 * @Date: 2021/11/04
 * @Description 统一缓存优化
 *
 * 只对于需要频繁查询的内容进行缓存优化
 *
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    // 缓存过期时间 毫秒值
    long expire() default 1 * 60 * 1000;
    // 缓存名称
    String name() default "";
}
