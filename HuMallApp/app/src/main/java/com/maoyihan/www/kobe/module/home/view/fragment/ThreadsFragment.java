package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;
import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.ThreadsBean;
import com.maoyihan.www.kobe.module.home.view.adapter.ThreadsAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 帖子
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class ThreadsFragment extends BaseFragment {
    @Bind(R.id.newsFg_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @Bind(R.id.newsFg_recycler)
    RecyclerView recyclerView;

    private ThreadsAdapter mThreadsAdapter;

    public static ThreadsFragment newInstance() {
        Bundle args = new Bundle();
        ThreadsFragment fragment = new ThreadsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.common_single_list, null);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        initRecyclerView();
    }

    @Override
    protected void initData() {
        getNews();
    }

    @Override
    protected void initListener() {
        AppBarLayout appBarLayout = (AppBarLayout)mActivity.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset == 0){
                    smartRefreshLayout.setEnabled(true);
                }else{
                    smartRefreshLayout.setEnabled(false);
                }
            }
        });
        smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNews();
            }
        });
    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mThreadsAdapter = new ThreadsAdapter();
        recyclerView.setAdapter(mThreadsAdapter);
    }

    private void getNews() {
        RetrofitUtil.getInstance().api().getThreads()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ThreadsBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ThreadsBean threadsBean) {
                        mThreadsAdapter.setNewData(threadsBean.getResult().getData());
                        smartRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        smartRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//                .subscribe(new Subscriber<ThreadsBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        smartRefreshLayout.setRefreshing(false);
//                    }
//
//                    @Override
//                    public void onNext(ThreadsBean threadsBean) {
//                        mThreadsAdapter.setNewData(threadsBean.getResult().getData());
//                        smartRefreshLayout.setRefreshing(false);
//                    }
//                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
