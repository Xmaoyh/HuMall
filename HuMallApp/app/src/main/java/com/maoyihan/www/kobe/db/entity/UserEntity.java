package com.maoyihan.www.kobe.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author MaoYiHan
 * @date 2018/4/12
 * @describe 用户实体类
 */
@Entity
public class UserEntity {
    @PrimaryKey
    private int uid;
    private String name;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
