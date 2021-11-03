package com.arimsky.blogapi.service;

import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.vo.CategoryVo;

/**
 * @ClassName: CategoryService
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description
 */

public interface CategoryService {
    CategoryVo findCategoryById(Integer categoryId);

    ResultData<Object> finAll();
}
