package com.arimsky.blogapi.dao;

import com.arimsky.blogapi.pojo.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: CategoryMapper
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}
