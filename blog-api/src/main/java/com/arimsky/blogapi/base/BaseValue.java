package com.arimsky.blogapi.base;

/**
 * @ClassName: BaseValue
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description 突然发现有的值重复使用了
 */

public enum BaseValue {
    /**
     * md5 密码加盐的值
     */
    SALT("ariBlog!@#");

    private final String value;

    BaseValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
