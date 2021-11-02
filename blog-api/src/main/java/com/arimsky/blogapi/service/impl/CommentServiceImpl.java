package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.dao.CommentMapper;
import com.arimsky.blogapi.pojo.entity.Comment;
import com.arimsky.blogapi.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

}
