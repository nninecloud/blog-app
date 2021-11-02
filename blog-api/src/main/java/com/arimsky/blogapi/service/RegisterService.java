package com.arimsky.blogapi.service;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.vo.LoginParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: RegisterService
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */

@Transactional
public interface RegisterService {

    ResultData<Object> register(LoginParam loginParam);
}
