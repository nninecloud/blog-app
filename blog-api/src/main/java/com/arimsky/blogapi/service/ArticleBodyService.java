package com.arimsky.blogapi.service;

import com.arimsky.blogapi.pojo.entity.ArticleBody;
import java.util.List;

/**
 * (ArticleBody)表服务接口
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public interface ArticleBodyService {

    /**
     * 通过ID查询单条数据
     *
     * @param bodyId 主键
     * @return 实例对象
     */
    ArticleBody queryById(Long bodyId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ArticleBody> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param articleBody 实例对象
     * @return 实例对象
     */
    ArticleBody insert(ArticleBody articleBody);

    /**
     * 修改数据
     *
     * @param articleBody 实例对象
     * @return 实例对象
     */
    ArticleBody update(ArticleBody articleBody);

    /**
     * 通过主键删除数据
     *
     * @param bodyId 主键
     * @return 是否成功
     */
    boolean deleteById(Long bodyId);

}