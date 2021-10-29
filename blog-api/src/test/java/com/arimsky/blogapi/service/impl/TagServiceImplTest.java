package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.service.TagService;
import com.arimsky.blogapi.vo.TagVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName: TagServiceImplTest
 * @author: aRimsiky
 * @date: 2021/10/27
 * @description
 */

@SpringBootTest
class TagServiceImplTest {

    @Autowired
    private TagService tagService;

    @Test
    void hotTags() {
        List<TagVo> tagVoList = tagService.hotTags(6);
        Assertions.assertNotNull(tagVoList);
    }
}