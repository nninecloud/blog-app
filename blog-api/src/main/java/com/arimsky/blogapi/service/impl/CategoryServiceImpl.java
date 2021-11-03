package com.arimsky.blogapi.service.impl;

import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.dao.CategoryMapper;
import com.arimsky.blogapi.pojo.entity.Category;
import com.arimsky.blogapi.service.CategoryService;
import com.arimsky.blogapi.vo.CategoryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        categoryVo.setId((long) Math.toIntExact(category.getCategoryId()));
        categoryVo.setAvatar(category.getAvatar());
        categoryVo.setCategoryName(category.getCategoryName());


        return categoryVo;
    }

    @Override
    public ResultData<Object> finAll() {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Category::getCategoryId, Category::getCategoryName);
        //noinspection unchecked
        wrapper.orderByAsc(Category::getCategoryId);

        List<Category> categories = categoryMapper.selectList(wrapper);

        return ResultData.success(copyList(categories));
    }

    @Override
    public ResultData<Object> finAllDetail() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        //noinspection unchecked
        wrapper.orderByAsc(Category::getCategoryId);
        List<Category> categories = categoryMapper.selectList(wrapper);

        return ResultData.success(copyList(categories));
    }

    @Override
    public ResultData<Object> categoriesDetailById(Long id) {

        if (id == null) {
            return ResultData.error(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMessage());
        }

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getCategoryId, id);
        Category category = categoryMapper.selectOne(wrapper);

        return ResultData.success(copy(category));
    }

    private List<CategoryVo> copyList(List<Category> categories) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categories) {
            categoryVoList.add(copy(category));
        }

        return categoryVoList;
    }

    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setId((long) Math.toIntExact(category.getCategoryId()));
        BeanUtils.copyProperties(category, categoryVo);
/*
        categoryVo.setAvatar(category.getAvatar());
        categoryVo.setCategoryName(category.getCategoryName());
        categoryVo.setDescription(category.getDescription());
*/

        return categoryVo;
    }
}
