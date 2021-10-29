package com.arimsky.blogapi.pojo.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 186919438381995582L;
    
    private Long id;
    
    private String username;
    
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}