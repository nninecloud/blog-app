package com.arimsky.blogapi.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ArticleBody)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Data
public class ArticleBody implements Serializable {
    private static final long serialVersionUID = 236424128078526402L;
    
    private Long bodyId;
    
    private String content;
    
    private String contentHtml;
    
    private Long articleId;




}