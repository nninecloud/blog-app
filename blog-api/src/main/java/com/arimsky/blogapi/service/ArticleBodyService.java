package com.arimsky.blogapi.service;

import com.arimsky.blogapi.vo.ArticleBodyVo;

/**
 * @ClassName: ArticleBodyService
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */

public interface ArticleBodyService {

    ArticleBodyVo findArticleBodyById(Long bodyId);
}
