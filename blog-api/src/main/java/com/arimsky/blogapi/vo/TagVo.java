package com.arimsky.blogapi.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: TagVo
 * @author: aRimsiky
 * @date: 2021/10/22
 * @description
 */

@Data
public class TagVo implements Serializable {
    private Long id;

    private String tagName;

    private String avatar;

}
