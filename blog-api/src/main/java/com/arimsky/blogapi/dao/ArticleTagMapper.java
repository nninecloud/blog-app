package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.ArticleTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * (ArticleTag)表数据库访问层
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Repository
public interface ArticleTagMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ArticleTag queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ArticleTag> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param articleTag 实例对象
     * @return 对象列表
     */
    List<ArticleTag> queryAll(ArticleTag articleTag);

    /**
     * 新增数据
     *
     * @param articleTag 实例对象
     * @return 影响行数
     */
    int insert(ArticleTag articleTag);

    /**
     * 修改数据
     *
     * @param articleTag 实例对象
     * @return 影响行数
     */
    int update(ArticleTag articleTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}