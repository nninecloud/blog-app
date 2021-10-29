package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.service.ArticleService;
import com.arimsky.blogapi.vo.ArticleVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName: ArticleServiceImplTest
 * @Author: aRimsiky
 * @Date: 2021/10/28
 * @Description
 */
@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void hotArticles() {
        List<ArticleVo> articleVoList = articleService.hotArticles(5);
        for (ArticleVo articleVo : articleVoList) {
            System.out.println(articleVo);
        }
        articleVoList.forEach(System.out::println);
    }
}