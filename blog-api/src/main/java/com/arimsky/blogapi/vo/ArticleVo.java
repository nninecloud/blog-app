package com.arimsky.blogapi.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ArticleVo
 * @description Article 的业务对象类
 * @author: aRimsiky
 * @date: 2021/10/22
 */

@Data
public class ArticleVo implements Serializable {

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private CategoryVo categorys;

    @Override
    public String toString() {
        return "ArticleVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", commentCounts=" + commentCounts +
                ", viewCounts=" + viewCounts +
                ", weight=" + weight +
                ", createDate='" + createDate + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                ", categorys=" + categorys +
                '}';
    }
}
