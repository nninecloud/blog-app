package com.arimsky.blogapi.base.exception;

/**
 * @ClassName: DataException
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description 数据异常
 */

public class DataException extends RuntimeException{

    public DataException(String message) {
        super(message);
    }
}
