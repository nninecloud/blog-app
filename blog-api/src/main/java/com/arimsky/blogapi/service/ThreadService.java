package com.arimsky.blogapi.service;

import com.arimsky.blogapi.dao.ArticleMapper;
import com.arimsky.blogapi.pojo.entity.Article;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    @Async("taskExecutor")
    public void updateViewCount(ArticleMapper articleMapper, Article article) {

        Integer viewCounts = article.getViewCounts();

        Article articleUpdate = new Article();

        articleUpdate.setViewCounts(viewCounts + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getArticleId, article.getArticleId());
        queryWrapper.eq(Article::getViewCounts, viewCounts);

        articleMapper.update(articleUpdate, queryWrapper);
//        try {
//            //睡眠5秒 证明不会影响主线程的使用
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}