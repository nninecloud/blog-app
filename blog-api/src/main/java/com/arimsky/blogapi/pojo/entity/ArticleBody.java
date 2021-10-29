package com.arimsky.blogapi.pojo.entity;

import java.io.Serializable;

/**
 * (ArticleBody)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class ArticleBody implements Serializable {
    private static final long serialVersionUID = 236424128078526402L;
    
    private Long bodyId;
    
    private Object content;
    
    private Object contentHtml;
    
    private Long articleId;


    public Long getBodyId() {
        return bodyId;
    }

    public void setBodyId(Long bodyId) {
        this.bodyId = bodyId;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(Object contentHtml) {
        this.contentHtml = contentHtml;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

}