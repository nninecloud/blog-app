package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.pojo.entity.ArticleTag;
import com.arimsky.blogapi.dao.ArticleTagMapper;
import com.arimsky.blogapi.service.ArticleTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ArticleTag)表服务实现类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Service("articleTagService")
public class ArticleTagServiceImpl implements ArticleTagService {
    @Resource
    private ArticleTagMapper articleTagMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ArticleTag queryById(Long id) {
        return this.articleTagMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ArticleTag> queryAllByLimit(int offset, int limit) {
        return this.articleTagMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param articleTag 实例对象
     * @return 实例对象
     */
    @Override
    public ArticleTag insert(ArticleTag articleTag) {
        this.articleTagMapper.insert(articleTag);
        return articleTag;
    }

    /**
     * 修改数据
     *
     * @param articleTag 实例对象
     * @return 实例对象
     */
    @Override
    public ArticleTag update(ArticleTag articleTag) {
        this.articleTagMapper.update(articleTag);
        return this.queryById(articleTag.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.articleTagMapper.deleteById(id) > 0;
    }
}