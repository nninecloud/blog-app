package com.arimsky.blogapi.pojo.entity;

import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = -12531098839216638L;
    
    private Long commentId;
    
    private String content;
    
    private Long createDate;
    
    private Integer articleId;
    
    private Long authorId;
    
    private Long parentId;
    
    private Long toUid;
    
    private String level;


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getToUid() {
        return toUid;
    }

    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}