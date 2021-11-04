package com.arimsky.blogapi.base.exception;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.base.ReturnCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 *  @ClassName: ExceptionHandlerBridge
 *  @author: aRimsiky
 *  @date: 2021/10/28
 *  @Description 不怎么地得优化
 */
@RestControllerAdvice
public class ExceptionHandlerBridge {

    //进行异常处理，处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    public ResultData<Object> doException(Exception ex){
        ex.printStackTrace();
        return ResultData.error(ReturnCode.RC999.getCode(),ReturnCode.RC999.getMessage());
    }

    @ExceptionHandler(UploadException.class)
    public ResultData<Object> uploadException(UploadException e){
        e.printStackTrace();
        return ResultData.error(e.getStatus(), e.getMessage());
    }

}
