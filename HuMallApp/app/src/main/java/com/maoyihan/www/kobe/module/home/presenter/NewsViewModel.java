package com.maoyihan.www.kobe.module.home.presenter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;

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
    public NewsViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init(){
        if(mNews == null){
            mNews = new MutableLiveData<>();
        }
        getNewsFormNet();
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
}
