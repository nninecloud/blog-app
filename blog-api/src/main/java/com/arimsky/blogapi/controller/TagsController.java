package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.service.TagService;
import com.arimsky.blogapi.vo.TagVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("tags")
public class TagsController {

    @Resource
    private TagService tagService;

    @GetMapping("/hot")
    public ResultData<Object> listHotTags() {
        int limit = 6;
        List<TagVo> tagVoList = tagService.hotTags(limit);
        return ResultData.success(tagVoList);
    }

}