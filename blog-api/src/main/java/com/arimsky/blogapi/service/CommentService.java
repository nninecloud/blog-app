package com.arimsky.blogapi.service;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.Comment;
import com.arimsky.blogapi.vo.params.CommentParam;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CommentService extends IService<Comment> {

    ResultData<Object> findCommentsByArticleId(Long articleId);

    ResultData<Object> createComment(CommentParam commentParam);
}
