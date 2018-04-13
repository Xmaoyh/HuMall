package com.maoyihan.www.kobe.module.home.presenter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.maoyihan.www.kobe.base.MyApplication;
import com.maoyihan.www.kobe.db.AppDatabase;
import com.maoyihan.www.kobe.db.entity.UserEntity;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author MaoYiHan
 * @date 2018/4/13
 * @describe
 */
public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<List<UserEntity>> mUserEntityList;
    public UserViewModel(@NonNull Application application) {
        super(application);
        if(mUserEntityList == null){
            mUserEntityList = new MutableLiveData<>();
        }
    }

    public void getUserFromDb(){
        MyApplication.getInstance().getAppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mUserEntityList.postValue(MyApplication.getInstance().getDatabase().userDao().getAll());
            }
        });
    }

    public void insertUser(List<UserEntity> userEntities){
        MyApplication.getInstance().getAppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                MyApplication.getInstance().getDatabase().userDao().insertAll(userEntities);
                getUserFromDb();
            }
        });
    }

    public void deleteUser(UserEntity userEntity){
        MyApplication.getInstance().getAppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                MyApplication.getInstance().getDatabase().userDao().delete(userEntity);
            }
        });
    }


    public void deleteMao(){
        MyApplication.getInstance().getDatabase().userDao().getAllLikeMao().
                subscribeOn(Schedulers.io()).
                observeOn(Schedulers.io()).
                subscribe(new SingleObserver<List<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("deleteMao","onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<UserEntity> userEntities) {
                        Log.d("deleteMao","onSuccess");
                        MyApplication.getInstance().getDatabase().userDao().deleteAll(userEntities);
                        mUserEntityList.postValue(MyApplication.getInstance().getDatabase().userDao().getAll());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("deleteMao","onError");
                    }
                });
    }

    public void updateUser(UserEntity userEntity){
        userEntity.setName("KKKK");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                MyApplication.getInstance().getDatabase().userDao().update(userEntity);
                e.onNext("success");
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mUserEntityList.postValue(MyApplication.getInstance().getDatabase().userDao().getAll());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public MutableLiveData<List<UserEntity>> getUserEntityList() {
        return mUserEntityList;
    }
}
