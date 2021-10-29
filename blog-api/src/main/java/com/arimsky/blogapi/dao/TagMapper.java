package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Tag)表数据库访问层
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> findTagsByArticleId(@Param("articleId") Long articleId);

    List<Long> findHotTagIds(@Param("limit")int limit);

    List<Tag> findTagsByTagIds(@Param("hotTagsIds")List<Long> hotTagsIds);
}