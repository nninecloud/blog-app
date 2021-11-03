package com.arimsky.blogapi.vo.params;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: LoginParam
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description 登录业务类
 */

@Data
@ToString
public class LoginParam {

    private String account;

    private String password;

    private String nickname;

}
