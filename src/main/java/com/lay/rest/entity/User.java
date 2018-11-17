package com.lay.rest.entity;

import com.lay.rest.entity.enumeration.SexEnum;

/**
 * @Description:
 * @Author: lay
 * @Date: Created in 13:11 2018/11/17
 * @Modified By:IntelliJ IDEA
 */
public class User {
    private Long id;
    private String userName;
    private SexEnum sex=null;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
