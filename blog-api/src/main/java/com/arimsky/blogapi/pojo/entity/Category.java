package com.arimsky.blogapi.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class Category implements Serializable {
    private static final long serialVersionUID = -49248804010545427L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;
    
    private String avatar;
    
    private String categoryName;
    
    private String description;


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}