package com.arimsky.blogapi.controller;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: CategoryController
 * @Author: aRimsiky
 * @Date: 2021/11/02
 * @Description 文章分类
 */

@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 查询所有的分类, 文章发布显示
     * @return List<CategoryVo>
     */
    @GetMapping
    public ResultData<Object> categorys(){
        return categoryService.finAll();
    }

    /**
     * 文章分类 详情
     * @return List<CategoryVo>
     */
    @GetMapping("detail")
    public ResultData<Object> categoryDetail(){
        return categoryService.finAllDetail();
    }

    @GetMapping("detail/{id}")
    public ResultData<Object> categoriesDetailById(@PathVariable("id") Long id){
        return categoryService.categoriesDetailById(id);
    }
}
