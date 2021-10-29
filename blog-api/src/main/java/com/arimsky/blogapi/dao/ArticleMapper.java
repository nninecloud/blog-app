package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.Article;
import com.arimsky.blogapi.vo.Archives;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();

    List<Article> listArticle();
}