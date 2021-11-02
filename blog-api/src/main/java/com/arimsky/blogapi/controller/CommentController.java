package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.service.CommentService;
import com.arimsky.blogapi.vo.CommentParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: CommentController
 * @Author: aRimsiky
 * @Date: 2021/11/02
 * @Description 评论控制器
 */

@RestController
@RequestMapping("comments")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 评论
     */
    @GetMapping("article/{id}")
    public ResultData<Object> comments(@PathVariable("id") Long articleId){

         return commentService.findCommentsByArticleId(articleId);
    }

    /**
     * 创建评论
     */
    @PostMapping("create/change")
    public ResultData<Object> createComment(@RequestBody CommentParam commentParam){
        return commentService.createComment(commentParam);
    }
}
