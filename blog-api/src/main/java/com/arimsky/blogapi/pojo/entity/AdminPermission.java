package com.arimsky.blogapi.pojo.entity;

import java.io.Serializable;

/**
 * (AdminPermission)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class AdminPermission implements Serializable {
    private static final long serialVersionUID = -82422908700685084L;
    
    private Long id;
    
    private Long adminId;
    
    private Long permissionId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}