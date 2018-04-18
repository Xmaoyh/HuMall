package com.maoyihan.www.kobe.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.maoyihan.www.kobe.db.entity.UserEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.DELETE;

/**
 * @author MaoYiHan
 * @date 2018/4/12
 * @describe 用户dao
 */
@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity userEntity);

    @Insert
    void insertAll(List<UserEntity> userEntities);

    @Delete
    void delete(UserEntity userEntity);

    @Delete
    void deleteAll(List<UserEntity> userEntities);

    @Update()
    void update(UserEntity userEntity);

    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAll();

    @Query("SELECT * FROM UserEntity WHERE uid  = :uid")
    Flowable<UserEntity> getByUid(int uid);

    @Query("SELECT * FROM userentity WHERE name like 'Mao%'")
    Single<List<UserEntity>> getAllLikeMao();

}
