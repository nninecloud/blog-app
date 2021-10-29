package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.pojo.entity.ArticleBody;
import com.arimsky.blogapi.dao.ArticleBodyMapper;
import com.arimsky.blogapi.service.ArticleBodyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ArticleBody)表服务实现类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Service("articleBodyService")
public class ArticleBodyServiceImpl implements ArticleBodyService {
    @Resource
    private ArticleBodyMapper articleBodyMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param bodyId 主键
     * @return 实例对象
     */
    @Override
    public ArticleBody queryById(Long bodyId) {
        return this.articleBodyMapper.queryById(bodyId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ArticleBody> queryAllByLimit(int offset, int limit) {
        return this.articleBodyMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param articleBody 实例对象
     * @return 实例对象
     */
    @Override
    public ArticleBody insert(ArticleBody articleBody) {
        this.articleBodyMapper.insert(articleBody);
        return articleBody;
    }

    /**
     * 修改数据
     *
     * @param articleBody 实例对象
     * @return 实例对象
     */
    @Override
    public ArticleBody update(ArticleBody articleBody) {
        this.articleBodyMapper.update(articleBody);
        return this.queryById(articleBody.getBodyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param bodyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long bodyId) {
        return this.articleBodyMapper.deleteById(bodyId) > 0;
    }
}