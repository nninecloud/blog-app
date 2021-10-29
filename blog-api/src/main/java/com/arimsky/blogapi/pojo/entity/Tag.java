package com.arimsky.blogapi.pojo.entity;

import lombok.ToString;

import java.io.Serializable;

/**
 * (Tag)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@ToString
public class Tag implements Serializable {
    private static final long serialVersionUID = -86916806464546532L;
    
    private Long id;
    
    private String avatar;
    
    private String tagName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}