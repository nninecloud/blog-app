package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.ArticleBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (ArticleBody)表数据库访问层
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Repository
public interface ArticleBodyMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param bodyId 主键
     * @return 实例对象
     */
    ArticleBody queryById(Long bodyId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ArticleBody> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param articleBody 实例对象
     * @return 对象列表
     */
    List<ArticleBody> queryAll(ArticleBody articleBody);

    /**
     * 新增数据
     *
     * @param articleBody 实例对象
     * @return 影响行数
     */
    int insert(ArticleBody articleBody);

    /**
     * 修改数据
     *
     * @param articleBody 实例对象
     * @return 影响行数
     */
    int update(ArticleBody articleBody);

    /**
     * 通过主键删除数据
     *
     * @param bodyId 主键
     * @return 影响行数
     */
    int deleteById(Long bodyId);

}