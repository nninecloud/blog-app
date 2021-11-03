package com.arimsky.blogapi.service.impl;
import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.dao.CommentMapper;
import com.arimsky.blogapi.pojo.entity.Comment;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.service.CommentService;
import com.arimsky.blogapi.service.SysUserService;
import com.arimsky.blogapi.utils.UserThreadLocal;
import com.arimsky.blogapi.vo.params.CommentParam;
import com.arimsky.blogapi.vo.CommentVo;
import com.arimsky.blogapi.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private SysUserService sysUserService;


    @Override
    public ResultData<Object> findCommentsByArticleId(Long articleId) {

        if (!StringUtils.hasText(String.valueOf(articleId))){
            return ResultData.error(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMessage());
        }

        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        //noinspection unchecked
        wrapper.eq(Comment::getArticleId, articleId)
                .eq(Comment::getLevel, 1)
                .orderByDesc(Comment::getCreateDate);
        List<Comment> comments = commentMapper.selectList(wrapper);

        return ResultData.success(copyList(comments));
    }

    @Override
    public ResultData<Object> createComment(CommentParam commentParam) {

        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());

        Long parent = commentParam.getParent();
        if (parent == null || parent == 0L){
            comment.setLevel(1);
            comment.setParentId(0L);
        }else {
            comment.setLevel(2);
            comment.setParentId(parent);
        }
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null? 0L : toUserId);

        commentMapper.insert(comment);


        return ResultData.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {

        List<CommentVo> commentVoList = new ArrayList<>();

        for (Comment comment : comments) {
            CommentVo commentVo = copy(comment);
            commentVoList.add(commentVo);
        }

        return commentVoList;
    }

    private CommentVo copy(Comment comment) {

        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);

        commentVo.setId(String.valueOf(comment.getCommentId()));

        commentVo.setContent(comment.getContent());
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
//        commentVo.setLevel(1);

        List<CommentVo> childComments = findCommentsByParentId(comment.getCommentId());
        commentVo.setChildrens(childComments);
        // 作者
        Long authorId = comment.getAuthorId();
        UserVo author = sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(author);

        //子评论
        Integer level = comment.getLevel();
        if (1 == level){
            Long id = comment.getCommentId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        //to User 给谁评论
        if (level > 1){
            Long toUid = comment.getToUid();
            UserVo toUserVo = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }


        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long parentId) {

        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getParentId, parentId).eq(Comment::getLevel, 2);

        List<Comment> comments = commentMapper.selectList(wrapper);

        return copyList(comments);
    }
}
