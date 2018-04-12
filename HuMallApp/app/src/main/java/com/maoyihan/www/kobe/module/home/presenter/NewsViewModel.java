package com.maoyihan.www.kobe.module.home.presenter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.maoyihan.www.kobe.base.MyApplication;
import com.maoyihan.www.kobe.db.AppDatabase;
import com.maoyihan.www.kobe.db.entity.UserEntity;
import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author MaoYiHan
 * @date 2018/4/11
 * @describe
 */
public class NewsViewModel extends AndroidViewModel {
    private MutableLiveData<NewsBean> mNews;
    private List<UserEntity> mUserEntityList;
    public NewsViewModel(@NonNull Application application/*,AppDatabase appDatabase*/) {
        super(application);
        init(/*appDatabase*/);
    }

    private void init(/*AppDatabase appDatabase*/){
        if(mNews == null){
            mNews = new MutableLiveData<>();
        }
        getNewsFormNet();
        MyApplication.getInstance().getAppExecutors().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mUserEntityList = MyApplication.getInstance().getDatabase().userDao().getAll();
            }
        });
    }

    private void getNewsFormNet() {
        RetrofitUtil.getInstance().api().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        mNews.setValue(newsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mNews.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<NewsBean> getNewBean() {
        getNewsFormNet();
        return mNews;
    }

//    /**
//     * A creator is used to inject the product ID into the ViewModel
//     * <p>
//     * This creator is to showcase how to inject dependencies into ViewModels. It's not
//     * actually necessary in this case, as the product ID can be passed in a public method.
//     */
//    public static class Factory extends ViewModelProvider.NewInstanceFactory {
//
//        @NonNull
//        private final Application mApplication;
//
//        private final AppDatabase mAppDatabase;
//
//        public Factory(@NonNull Application application,AppDatabase appDatabase) {
//            mApplication = application;
//            mAppDatabase = appDatabase;
//        }
//
//        @Override
//        public <T extends ViewModel> T create(Class<T> modelClass) {
//            //noinspection unchecked
//            return (T) new NewsViewModel(mApplication, mAppDatabase);
//        }
//    }
}
