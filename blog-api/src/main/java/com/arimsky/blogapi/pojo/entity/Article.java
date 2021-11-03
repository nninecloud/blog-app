package com.arimsky.blogapi.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@ToString
public class Article implements Serializable {
    private static final long serialVersionUID = 395259022157314920L;

    // 置顶
    public static final int Article_TOP = 1;
    // 存入时给定基础权重
    public static final int Article_Common = 0;

    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;
    /**
    * 评论数量
    */
    private Integer commentCounts;
    /**
    * 创建时间  事件戳
    */
    private Long createDate;
    /**
    * 简介/概述
    */
    private String summary;
    /**
    * 标题
    */
    private String title;
    /**
    * 浏览数量
    */
    private Integer viewCounts;
    /**
    * 是否置顶
    */
    private Integer weight;
    /**
    * 作者id
    */
    private Long authorId;
    /**
    * 内容id
    */
    private Long bodyId;
    /**
    * 类别id
    */
    private Integer categoryId;


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewCounts() {
        return viewCounts;
    }

    public void setViewCounts(Integer viewCounts) {
        this.viewCounts = viewCounts;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBodyId() {
        return bodyId;
    }

    public void setBodyId(Long bodyId) {
        this.bodyId = bodyId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}