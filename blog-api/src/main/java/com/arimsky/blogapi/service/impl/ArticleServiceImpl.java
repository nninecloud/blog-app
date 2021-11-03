package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.dao.ArticleBodyMapper;
import com.arimsky.blogapi.dao.ArticleMapper;
import com.arimsky.blogapi.dao.ArticleTagMapper;
import com.arimsky.blogapi.pojo.entity.Article;
import com.arimsky.blogapi.pojo.entity.ArticleBody;
import com.arimsky.blogapi.pojo.entity.ArticleTag;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.service.*;
import com.arimsky.blogapi.utils.UserThreadLocal;
import com.arimsky.blogapi.vo.*;
import com.arimsky.blogapi.vo.params.ArticleParam;
import com.arimsky.blogapi.vo.params.PageBean;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private TagService tagsService;
    @Resource
    private ArticleBodyService articleBodyService;
    @Resource
    private CategoryService categoryService;


    @Override
    public List<ArticleVo> listArticlesPage(PageBean pageBean) {

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        if (pageBean.getCategoryId() != null) {
            wrapper.eq(Article::getCategoryId, pageBean.getCategoryId());
        }

        List<Long> articleIdList = new ArrayList<>();

        if (pageBean.getTagId() != null) {
            LambdaQueryWrapper<ArticleTag> articleTagWrapper = new LambdaQueryWrapper<>();
            articleTagWrapper.eq(ArticleTag::getTagId, pageBean.getTagId());
            List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagWrapper);
            for (ArticleTag articleTag : articleTags) {
                articleIdList.add(articleTag.getArticleId());
            }
            if (articleIdList.size() > 0) {
                wrapper.in(Article::getArticleId, articleIdList);
            }
        }

        //noinspection unchecked
        wrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
        Page<Article> page = new Page<>(pageBean.getPage(), pageBean.getPageSize());

        Page<Article> pages = articleMapper.selectPage(page, wrapper);
//        List<ArticleVo> articleVoList = copyList(pages.getRecords(), true,  true);

        return copyList(pages.getRecords(), true, true);
    }

    @Override
    public List<ArticleVo> hotArticles(int limit) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_counts");
        wrapper.select("article_id", "title");
        wrapper.last("limit " + limit);

        List<Article> articles = articleMapper.selectList(wrapper);
        return copyList(articles, false, false);
    }

    @Override
    public List<ArticleVo> newArticles(int limit) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        //noinspection unchecked
        wrapper.orderByDesc(Article::getCreateDate);
        wrapper.select(Article::getArticleId, Article::getTitle);
        wrapper.last("limit " + limit);

        List<Article> articles = articleMapper.selectList(wrapper);

        return copyList(articles, false, false);
    }

    /**
     * 查询时转换事件戳
     *
     * @return List<Archives>
     */
    @Override
    public List<Archives> listArchives() {
        return articleMapper.listArchives();
    }

    @Resource
    private ThreadService threadService;

    @Override
    public ArticleVo findArticleById(Long id) {

        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getArticleId, id);
        Article article = articleMapper.selectOne(wrapper);
        // 每访问一次， 访问次数加  1
        threadService.updateViewCount(articleMapper, article);
        // 加一后 更新 本次显示的访问次数
        article.setViewCounts(article.getViewCounts() + 1);

        return copy(article, true, true, true, true);
    }

    @Resource
    private ArticleTagMapper articleTagMapper;
    @Resource
    private ArticleBodyMapper articleBodyMapper;

    @Override
    public ResultData<Object> publish(ArticleParam articleParam) {

        SysUser sysUser = UserThreadLocal.get();

        Article article = new Article();

        article.setAuthorId(sysUser.getId());
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0);
        article.setWeight(Article.Article_Common);
        article.setCategoryId(Math.toIntExact(articleParam.getCategory().getId()));
        articleMapper.insert(article);

        //tags
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            for (TagVo tag : tags) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(tag.getId());
                articleTag.setArticleId(article.getArticleId());
                articleTagMapper.save(articleTag);
            }
        }
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(article.getArticleId());
        articleBodyMapper.insert(articleBody);

        article.setBodyId(articleBody.getBodyId());
        articleMapper.updateById(article);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getArticleId());


        return ResultData.success(articleVo);
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isAuthor, boolean isTags) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : records) {
            ArticleVo articleVo = copy(article, isAuthor, isTags);
            articleVoList.add(articleVo);
        }
        return articleVoList;
    }

    public ArticleVo copy(Article article, boolean isAuthor, boolean isTags) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);

        // 两个的id 没对应好手动设置
        articleVo.setId(article.getArticleId());
        if (isAuthor) {
            // 每一篇文章的作者都是本站的 User
            SysUser sysUser = sysUserService.findSysUserById(article.getAuthorId());
            articleVo.setAuthor(sysUser.getNickname());
        }
//        isBody 首页展示
        // joda 时间戳 转换 , 不好换了
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        articleVo.setCreateDate(String.format(new DateTime(article.getCreateDate()).toString(), "yyyy-MM-dd HH:mm"));
        articleVo.setCreateDate(formatter.format(article.getCreateDate()));

        if (isTags) {
            List<TagVo> tags = tagsService.findTagsByArticleId(article.getArticleId());
            articleVo.setTags(tags);
        }
        return articleVo;
    }



    public ArticleVo copy(Article article, boolean isAuthor,
                          boolean isTags, boolean isBody, boolean isCategory) {

        ArticleVo articleVo = copy(article, isAuthor, isTags);

        if (isBody) {
            ArticleBodyVo articleBodyVo = articleBodyService.findArticleBodyById(article.getBodyId());
            articleVo.setBody(articleBodyVo);
        }

        if (isCategory) {
            CategoryVo categoryVo = categoryService.findCategoryById(article.getCategoryId());
            articleVo.setCategory(categoryVo);
        }

        return articleVo;
    }

}