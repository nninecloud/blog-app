package com.arimsky.blogapi.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author arimsky
 * @since 2021-10-22 16:00:02
 */
@TableName("blog_sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 805507776346449656L;

//    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
    * 账号
    */
    private String account;
    /**
    * 是否管理员
    */
    private Object admin;
    /**
    * 头像
    */
    private String avatar;
    /**
    * 注册时间
    */
    private Long createDate;
    /**
    * 是否删除
    */
    private Object deleted;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 最后登录时间
    */
    private Long lastLogin;
    /**
    * 手机号
    */
    private String mobilePhoneNumber;
    /**
    * 昵称
    */
    private String nickname;
    /**
    * 密码
    */
    private String password;
    /**
    * 加密盐
    */
    private String salt;
    /**
    * 状态
    */
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Object getAdmin() {
        return admin;
    }

    public void setAdmin(Object admin) {
        this.admin = admin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}