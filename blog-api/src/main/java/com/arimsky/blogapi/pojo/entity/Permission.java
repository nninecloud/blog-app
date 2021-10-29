package com.arimsky.blogapi.pojo.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -96615440138501278L;
    
    private Long id;
    
    private String name;
    
    private String path;
    
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}