package com.arimsky.blogapi.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (ArticleBody)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Data
@TableName("blog_article_body")
public class ArticleBody implements Serializable {
    private static final long serialVersionUID = 236424128078526402L;

    @TableId(value = "body_id", type = IdType.AUTO)
    private Long bodyId;
    
    private String content;
    
    private String contentHtml;
    
    private Long articleId;




}