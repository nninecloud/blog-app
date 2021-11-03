package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.dao.ArticleBodyMapper;
import com.arimsky.blogapi.pojo.entity.ArticleBody;
import com.arimsky.blogapi.service.ArticleBodyService;
import com.arimsky.blogapi.vo.ArticleBodyVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: ArticleBodyServiceImpl
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */
@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {

    @Resource
    private ArticleBodyMapper articleBodyMapper;


    @Override
    public ArticleBodyVo findArticleBodyById(Long bodyId) {
        LambdaQueryWrapper<ArticleBody> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(ArticleBody::getBodyId, bodyId);

        ArticleBody articleBody = articleBodyMapper.selectOne(wrapper);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();

        articleBodyVo.setContent(articleBody.getContent());

        return articleBodyVo;
    }
}
