package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.Article;
import com.arimsky.blogapi.pojo.entity.ArticleTag;
import com.arimsky.blogapi.vo.ArticleVo;
import com.arimsky.blogapi.vo.params.PageBean;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Test
    void listArchives() {
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        pageBean.setPageSize(5);
        pageBean.setYear("2021");
        pageBean.setMonth("5");

        Page<Article> page = new Page<>(pageBean.getPage(),pageBean.getPageSize());

        IPage<Article> articleIPage = articleMapper.listArticle(
                page,
                pageBean.getCategoryId(),
                pageBean.getTagId(),
                pageBean.getYear(),
                pageBean.getMonth());

        List<Article> records = articleIPage.getRecords();

        System.out.println(records.get(0));

    }

    @Test
    void listArticle() {
        PageBean pageBean = new PageBean(1, 10,null, null,null,null);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        if (pageBean.getCategoryId() != null) {
            wrapper.eq(Article::getCategoryId,pageBean.getCategoryId());
        }

        List<Long> articleIdList = new ArrayList<>();

        if (pageBean.getTagId() != null){
            LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
            articleTagWrapper.eq(ArticleTag::getTagId,pageBean.getTagId());
            List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagWrapper);
            for (ArticleTag articleTag : articleTags) {
                articleIdList.add(articleTag.getArticleId());
            }
            if (articleIdList.size() > 0){
                wrapper.in(Article::getArticleId,articleIdList);
            }
        }

        //noinspection unchecked
        wrapper.orderByDesc(Article::getWeight,Article::getCreateDate);
        Page<Article> page = new Page<>(pageBean.getPage(), pageBean.getPageSize());

        Page<Article> pages = articleMapper.selectPage(page, wrapper);
        List<Article> records = pages.getRecords();
        Article article = records.get(1);
        Long date = article.getCreateDate();
        System.out.println(article.getCreateDate());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setCreateDate(dateString);
        System.out.println(articleVo.getCreateDate());//"yyyy-MM-dd HH:mm"
        System.out.println(formatter.format(article.getCreateDate()));
//        System.out.println(formatter.format(0)));
        System.out.println(String.format(new DateTime(date).toString(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(String.format(new DateTime(0).toString(), "yyyy-MM-dd HH:mm:ss"));
    }
}