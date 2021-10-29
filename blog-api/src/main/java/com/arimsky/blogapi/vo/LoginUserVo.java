package com.arimsky.blogapi.vo;

import lombok.Data;

/**
 * @ClassName: LoginUserVo
 * @Author: aRimsiky
 * @Date: 2021/10/29
 * @Description
 */

@Data
public class LoginUserVo {
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
