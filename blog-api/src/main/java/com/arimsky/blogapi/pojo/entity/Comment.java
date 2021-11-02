package com.arimsky.blogapi.pojo.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Comment)实体类
 *   分布式id 比较长，传到前端 会有精度损失，必须转为string类型 进行传输，就不会有问题了
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Data
@ToString
public class Comment implements Serializable {
    private static final long serialVersionUID = -12531098839216638L;

    //防止前端 精度损失 把id转为string
//    @JsonSerialize(using = ToStringSerializer.class)
    private Long commentId;
    
    private String content;
    
    private Long createDate;

    /**
     * 本文章 id
     */
    private Long articleId;

    /**
     * 评论人
     */
    private Long authorId;

    /**
     * 上一楼
     */
    private Long parentId;

    /**
     * 文章作者
     */
    private Long toUid;

    /**
     * 当前评论为 1 级, 评论里的评论等级 +1
     */
    private Integer level;


}