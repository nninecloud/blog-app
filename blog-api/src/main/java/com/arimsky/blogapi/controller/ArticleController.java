package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.common.aop.LogAnnotation;
import com.arimsky.blogapi.service.ArticleService;
import com.arimsky.blogapi.vo.Archives;
import com.arimsky.blogapi.vo.ArticleVo;
import com.arimsky.blogapi.vo.params.ArticleParam;
import com.arimsky.blogapi.vo.params.PageBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Article)表控制层
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@RestController
@RequestMapping("articles")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    //@RequestParam(value = "page", defaultValue = "1")

    /**
     * 显示主页的文章列表,不排序
     */
    @PostMapping
    //加上此注解 代表要对此接口记录日志
    @LogAnnotation(module="文章",operation="获取文章列表")
//    @Cache(expire = 5 * 60 * 1000,name = "list_Articles")
    public ResultData<Object> articles(@RequestBody PageBean pageBean){
        List<ArticleVo> articleVoList = articleService.listArticlesPage(pageBean);

        return ResultData.success(articleVoList);
    }

    /**
     * 最热文章
     */
    @PostMapping("/hot")
//    @Cache(expire = 5 * 60 * 1000,name = "hot_article")
    public ResultData<Object> hotArticles(){
        int limit = 5;
        List<ArticleVo> articleVoList = articleService.hotArticles(limit);
        return ResultData.success(articleVoList);
    }

    /**
     * 最新文章
     */
    @PostMapping("/new")
//    @Cache(expire = 5 * 60 * 1000,name = "news_article")
    public ResultData<Object> newArticles(){
        int limit = 5;
        List<ArticleVo> articleVoList = articleService.newArticles(limit);
        return ResultData.success(articleVoList);
    }

    /**
     * 文当归档,按日期 每月归档
     *   "data": [
     *     {
     *       "year": 2021,
     *       "month": 5,
     *       "count": 1
     *     },
     *   ]
     *   把时间戳转换成年月日
     */
    @PostMapping("/listArchives")
//    @LogAnnotation(module = "文章", operation = "文章归档")
    public ResultData<Object> listArchives(){
        List<Archives> archivesList = articleService.listArchives();
        return ResultData.success(archivesList);
    }

    /**
     * 文章详情
     */
    @PostMapping("/view/{id}")
    public ResultData<Object> findArticleById(@PathVariable("id") Long id) {
        ArticleVo articleVo = articleService.findArticleById(id);

        return ResultData.success(articleVo);
    }

    /**
     * 写文章,发布
     * @param articleParam 写文章,发布封装参数
     * @return articleVo 封装后的文章对象
     */
    @PostMapping("publish")
    @LogAnnotation(module="文章",operation="写文章,发布")
//    @Cache(expire = 5 * 60 * 1000,name = "publish")
    public ResultData<Object> publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }

}