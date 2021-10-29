package com.arimsky.blogapi.base;

import lombok.Data;

/**
 * @author aRimsiky
 */
@Data
public class ResultData<T> {
  private Boolean success;
  /** 结果状态 ,具体状态码参见ResultData.java*/
  private int code;
  private String msg;
  private T data;
  // 时间戳
//  private long timestamp;


//  public ResultData (){
//    this.timestamp = System.currentTimeMillis();
//  }


  public static <T> ResultData<T> success(T data) {
    ResultData<T> resultData = new ResultData<>();
    resultData.setSuccess(true);
    resultData.setCode(ReturnCode.RC200.getCode());
    resultData.setMsg(ReturnCode.RC200.getMessage());
    resultData.setData(data);
    return resultData;
  }

  public static <T> ResultData<T> error(int code, String message) {
    ResultData<T> resultData = new ResultData<>();
    resultData.setSuccess(false);
    resultData.setCode(code);
    resultData.setMsg(message);
    resultData.setData(null);
    return resultData;
  }

}