package com.arimsky.blogapi.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * (ArticleTag)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Data
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 572862250035861758L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    private Long articleId;
    
    private Long tagId;



}