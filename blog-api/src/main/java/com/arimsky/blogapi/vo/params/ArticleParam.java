package com.arimsky.blogapi.vo.params;

import com.arimsky.blogapi.vo.CategoryVo;
import com.arimsky.blogapi.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * 写文章
 */
@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}