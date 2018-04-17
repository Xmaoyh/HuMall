package com.maoyihan.www.kobe.base;

import com.maoyihan.www.kobe.db.AppDatabase;
import com.maoyihan.www.kobe.db.entity.UserEntity;
import com.maoyihan.www.kobe.http.IApiAction;
import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;
import com.maoyihan.www.kobe.module.home.bean.NewsDetailBean;
import com.maoyihan.www.kobe.module.home.bean.PhotoArticleBean;
import com.maoyihan.www.kobe.module.home.bean.ThreadDetailBean;
import com.maoyihan.www.kobe.module.home.bean.ThreadsBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据源管理
 *
 * @author MaoYiHan
 * @date 2018/4/17
 */
public class DataRepository {
    private AppDatabase mAppDatabase;
    private IApiAction mApiAction;

    private static class DataRepositoryHolder {
        public static DataRepository instance = new DataRepository();
    }

    private DataRepository() {
        mAppDatabase = AppDatabase.getInstance();
        mApiAction = RetrofitUtil.getInstance().api();
    }

    public static DataRepository getInstance() {
        return DataRepositoryHolder.instance;
    }

    //-------------------------数据库----------------------//
    public void insert(UserEntity userEntity) {
        mAppDatabase.userDao().insert(userEntity);
    }

    public void insertAll(List<UserEntity> userEntities) {
        mAppDatabase.userDao().insertAll(userEntities);
    }

    public void delete(UserEntity userEntity) {
        mAppDatabase.userDao().delete(userEntity);
    }

    public void deleteAll(List<UserEntity> userEntities) {
        mAppDatabase.userDao().deleteAll(userEntities);
    }

    public void update(UserEntity userEntity) {
        mAppDatabase.userDao().update(userEntity);
    }

    public List<UserEntity> getAll() {
        return mAppDatabase.userDao().getAll();
    }

    public Flowable<UserEntity> getByUid(int uid) {
        return mAppDatabase.userDao().getByUid(uid);
    }

    public Single<List<UserEntity>> getAllLikeMao() {
        return mAppDatabase.userDao().getAllLikeMao();
    }

    //-------------------------网络----------------------//
    public Observable<NewsBean> getNews() {
        return mApiAction.getNews();
    }

    public Observable<NewsDetailBean> getNewsDetail(String nid) {
        return mApiAction.getNewsDetail(nid);
    }

    public Observable<ThreadsBean> getThreads() {
        return mApiAction.getThreads();
    }

    public Observable<ThreadDetailBean> getThreadDetail(String tid) {
        return mApiAction.getThreadDetail(tid);
    }

    public Observable<PhotoArticleBean> getPhotoArticle(String category, String time) {
        return mApiAction.getPhotoArticle(category, time);
    }
}
