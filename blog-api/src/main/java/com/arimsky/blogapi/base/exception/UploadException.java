package com.arimsky.blogapi.base.exception;

import lombok.Getter;

/**
 * @ClassName: UploadException
 * @Author: aRimsiky
 * @Date: 2021/11/04
 * @Description
 */

@Getter
public class UploadException extends RuntimeException{

    private Integer status;

    public UploadException(Integer status, String message) {
        super(message);
        this.status = status;
    }
}
