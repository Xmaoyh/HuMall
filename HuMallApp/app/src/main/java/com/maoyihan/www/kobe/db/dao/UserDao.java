package com.maoyihan.www.kobe.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.maoyihan.www.kobe.db.entity.UserEntity;

import java.util.List;

/**
 * @author MaoYiHan
 * @date 2018/4/12
 * @describe 用户dao
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAll();

    @Query("SELECT * FROM UserEntity WHERE uid  = :userId")
    List<UserEntity> loadAllByIds(int userId);

    @Insert
    void insertAll(List<UserEntity> userEntities);

    @Delete
    void delete(UserEntity userEntity);

//    @Update()
//    void update();
}
