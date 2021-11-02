package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.dao.CategoryMapper;
import com.arimsky.blogapi.pojo.entity.Category;
import com.arimsky.blogapi.service.CategoryService;
import com.arimsky.blogapi.vo.CategoryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: CategoryServiceImpl
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Integer categoryId) {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryId, categoryId);

        Category category = categoryMapper.selectOne(wrapper);

        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setId(category.getCategoryId());
        categoryVo.setAvatar(category.getAvatar());
        categoryVo.setCategoryName(category.getCategoryName());


        return categoryVo;
    }
}
