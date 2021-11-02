package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.vo.Archives;
import com.arimsky.blogapi.vo.ArticleVo;
import com.arimsky.blogapi.vo.PageBean;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.service.ArticleService;
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
    public ResultData<Object> articles(@RequestBody PageBean pageBean){
        List<ArticleVo> articleVoList = articleService.listArticlesPage(pageBean);

        return ResultData.success(articleVoList);
    }

    /**
     * 最热文章
     */
    @PostMapping("/hot")
    public ResultData<Object> hotArticles(){
        int limit = 5;
        List<ArticleVo> articleVoList = articleService.hotArticles(limit);
        return ResultData.success(articleVoList);
    }

    /**
     * 最新文章
     */
    @PostMapping("/new")
    public ResultData<Object> newArticles(){
        int limit = 5;
        List<ArticleVo> articleVoList = articleService.newArticles(limit);
        return ResultData.success(articleVoList);
    }

    /**
     * 文当归类,按日期
     */
    @PostMapping("/listArchives")
    public ResultData<Object> listArchives(){
        List<Archives> archivesList = articleService.listArchives();
        return ResultData.success(archivesList);
    }

    /**
     * 文章详情
     * @param id
     * @return
     */
    @PostMapping("/view/{id}")
    public ResultData<Object> findArticleById(@PathVariable("id") Long id) {
        ArticleVo articleVo = articleService.findArticleById(id);

        return ResultData.success(articleVo);
    }

}