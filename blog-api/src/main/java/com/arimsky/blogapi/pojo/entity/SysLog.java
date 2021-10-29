package com.arimsky.blogapi.pojo.entity;

import java.io.Serializable;

/**
 * (SysLog)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
public class SysLog implements Serializable {
    private static final long serialVersionUID = -59097787605957996L;
    
    private Long id;
    
    private Long createDate;
    
    private String ip;
    
    private String method;
    
    private String module;
    
    private String nickname;
    
    private String operation;
    
    private String params;
    
    private Long time;
    
    private Long userid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

}