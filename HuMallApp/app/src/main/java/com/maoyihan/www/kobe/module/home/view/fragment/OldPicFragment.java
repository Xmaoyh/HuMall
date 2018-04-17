package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;

import com.maoyihan.www.kobe.base.BaseListFragment;
import com.maoyihan.www.kobe.base.DataRepository;
import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.PhotoArticleBean;
import com.maoyihan.www.kobe.module.home.view.adapter.OldPicAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


/**
 * 老照片
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class OldPicFragment extends BaseListFragment {
    private String mTime;

    public static OldPicFragment newInstance() {
        Bundle args = new Bundle();
        OldPicFragment fragment = new OldPicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setAdapter() {
        mAdapter = new OldPicAdapter();
    }

    @Override
    protected void getData(boolean isRefresh) {
        DataRepository.getInstance().getPhotoArticle("gallery_old_picture", mTime)
                .subscribeOn(Schedulers.io())
                .switchMap(new Function<PhotoArticleBean, Observable<PhotoArticleBean.DataBean>>() {
                    @Override
                    public Observable<PhotoArticleBean.DataBean> apply(@NonNull PhotoArticleBean bean) throws Exception {
                        List<PhotoArticleBean.DataBean> data = bean.getData();
                        // 移除最后一项 数据有重复
                        if (data.size() > 0) {
                            data.remove(data.size() - 1);
                        }
                        mTime = bean.getNext().getMax_behot_time();
                        return Observable.fromIterable(data);
                    }
                })
                .filter(new Predicate<PhotoArticleBean.DataBean>() {
                    @Override
                    public boolean test(@NonNull PhotoArticleBean.DataBean dataBean) throws Exception {
                        // 去除重复新闻
                        for (PhotoArticleBean.DataBean bean : (List<PhotoArticleBean.DataBean>) mAdapter.getData()) {
                            if (dataBean.getTitle().equals(bean.getTitle())) {
                                return false;
                            }
                        }
                        return true;
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PhotoArticleBean.DataBean>>() {
                    @Override
                    public void accept(@NonNull List<PhotoArticleBean.DataBean> list) throws Exception {
                        if (isRefresh) {
                            mAdapter.setNewData(list);
                            smartRefreshLayout.setRefreshing(false);
                        } else {
                            if (list.size() > 0) {
                                mAdapter.addData(list);
                                mAdapter.loadMoreComplete();
                            } else {
                                mAdapter.loadMoreEnd();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (isRefresh) {
                            smartRefreshLayout.setRefreshing(false);
                        } else {
                            mAdapter.loadMoreFail();
                        }
                    }
                });

    }
}
