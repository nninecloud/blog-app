package com.arimsky.blogapi.service;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.vo.Archives;
import com.arimsky.blogapi.vo.ArticleVo;
import com.arimsky.blogapi.vo.params.ArticleParam;
import com.arimsky.blogapi.vo.params.PageBean;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public interface ArticleService {


    List<ArticleVo> listArticlesPage(PageBean pageBean);

    List<ArticleVo> hotArticles(int limit);

    List<ArticleVo> newArticles(int limit);

    List<Archives> listArchives();

    ArticleVo findArticleById(Long id);

    ResultData<Object> publish(ArticleParam articleParam);
}