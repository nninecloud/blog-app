package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.dao.TagMapper;
import com.arimsky.blogapi.pojo.entity.Tag;
import com.arimsky.blogapi.service.TagService;
import com.arimsky.blogapi.vo.TagVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Tag)表服务实现类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tagList = tagMapper.findTagsByArticleId(articleId);
        return copyList(tagList);
    }

    @Override
    public List<TagVo> hotTags(int limit) {

        List<Long> hotTagsIds = tagMapper.findHotTagIds(limit);
        if (CollectionUtils.isEmpty(hotTagsIds)) {
            return Collections.emptyList();
        }

        List<Tag> tagList = tagMapper.findTagsByTagIds(hotTagsIds);

        return copyList(tagList);
    }

    @Override
    public ResultData<Object> findAll() {

        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        //noinspection unchecked
        wrapper.select(Tag::getId, Tag::getTagName).orderByAsc(Tag::getId);

        List<Tag> tags = tagMapper.selectList(wrapper);

        return ResultData.success(copyList(tags));
    }

    @Override
    public ResultData<Object> findAllDetail() {
        //noinspection unchecked
        List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<Tag>().orderByAsc(Tag::getId));

        return ResultData.success(copyList(tags));

    }

    @Override
    public ResultData<Object> findDetailById(Long id) {

        if (id == null) {
            return ResultData.error(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMessage());
        }

        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getId, id);

        Tag tag = tagMapper.selectOne(wrapper);

        return ResultData.success(copy(tag));
    }

    private List<TagVo> copyList(List<Tag> records) {
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag record : records) {
            tagVoList.add(copy(record));
        }
        return tagVoList;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
//        tagVo.setId(tag.getId());
        return tagVo;
    }

}