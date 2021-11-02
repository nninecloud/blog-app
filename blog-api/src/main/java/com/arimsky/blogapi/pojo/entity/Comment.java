package com.arimsky.blogapi.pojo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Data
@ToString
public class Comment implements Serializable {
    private static final long serialVersionUID = -12531098839216638L;
    
    private Long commentId;
    
    private String content;
    
    private Long createDate;
    
    private Long articleId;
    
    private Long authorId;
    
    private Long parentId;
    
    private Long toUid;
    
    private Integer level;


}