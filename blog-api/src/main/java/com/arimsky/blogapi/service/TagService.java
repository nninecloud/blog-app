package com.arimsky.blogapi.service;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.vo.TagVo;

import java.util.List;

/**
 * (Tag)表服务接口
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public interface TagService {

    List<TagVo> findTagsByArticleId(Long articleId);

    List<TagVo> hotTags(int limit);

    ResultData<Object> findAll();

    ResultData<Object> findAllDetail();

    ResultData<Object> findDetailById(Long id);
}