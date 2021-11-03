package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName: ArticleMapperTest
 * @Author: aRimsiky
 * @Date: 2021/11/02
 * @Description
 */

@SpringBootTest
class ArticleMapperTest {

    @Resource
    private ArticleMapper articleMapper;

    @Test
    void listArchives() {
        Article article = new Article();
        article.setCommentCounts(0);
        article.setCreateDate(0L);
        article.setSummary("");
        article.setTitle("");
        article.setViewCounts(0);
        article.setWeight(0);
        article.setAuthorId(0L);
        article.setCategoryId(0);

        articleMapper.insert(article);

        System.out.println(article.getBodyId());


        articleMapper.updateById(article);
        System.out.println(article.getBodyId());
    }

    @Test
    void listArticle() {
    }
}