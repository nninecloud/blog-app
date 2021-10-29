package com.arimsky.blogapi.pojo.entity;

import java.io.Serializable;

/**
 * (ArticleTag)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 572862250035861758L;
    
    private Long id;
    
    private Long articleId;
    
    private Long tagId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

}