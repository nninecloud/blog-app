package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.ArticleTag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName: ArticleTagMapperTest
 * @Author: aRimsiky
 * @Date: 2021/11/02
 * @Description
 */

@SpringBootTest
class ArticleTagMapperTest {

    @Resource
    private ArticleTagMapper articleTagMapper;

    @Test
    void queryById() {
    }

    @Test
    void save() {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(0L);
        articleTag.setTagId(0L);

        articleTagMapper.save(articleTag);
        articleTagMapper.insert(articleTag);

        System.out.println(articleTag.getId());

    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}