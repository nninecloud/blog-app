package com.arimsky.blogapi.vo.params;

import lombok.Data;

/**
 * 写文章 body
 */
@Data
public class ArticleBodyParam {

    private String content;

    private String contentHtml;

}
