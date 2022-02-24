package com.arimsky.blogapi.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author arimsiky
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    /**
     * 模块名
     */
    String module() default "";

    /**
     * @return 操作信息
     */
    String operation() default "";
}