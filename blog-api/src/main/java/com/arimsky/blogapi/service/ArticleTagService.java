package com.arimsky.blogapi.service;

import com.arimsky.blogapi.pojo.entity.ArticleTag;
import java.util.List;

/**
 * (ArticleTag)表服务接口
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public interface ArticleTagService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ArticleTag queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ArticleTag> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param articleTag 实例对象
     * @return 实例对象
     */
    ArticleTag insert(ArticleTag articleTag);

    /**
     * 修改数据
     *
     * @param articleTag 实例对象
     * @return 实例对象
     */
    ArticleTag update(ArticleTag articleTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}