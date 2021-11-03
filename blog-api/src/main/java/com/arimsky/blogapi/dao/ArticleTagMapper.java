package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ArticleTagMapper
 * @Author: aRimsiky
 * @Date: 2021/11/02
 * @Description articleTagMapper
 */

@Repository
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ArticleTag queryById(Long id);

    /**
     * 新增数据
     *
     * @param blogArticleTag 实例对象
     * @return 影响行数
     */
    int save(ArticleTag blogArticleTag);

    /**
     * 修改数据
     *
     * @param blogArticleTag 实例对象
     * @return 影响行数
     */
    int update(ArticleTag blogArticleTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}
