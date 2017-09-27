package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;
import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;
import com.maoyihan.www.kobe.module.home.bean.ThreadsBean;
import com.maoyihan.www.kobe.module.home.view.adapter.NewsAdapter;
import com.maoyihan.www.kobe.module.home.view.adapter.ThreadsAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 帖子
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class ThreadsFragment extends BaseFragment {
    @Bind(R.id.newsFg_refresh)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.newsFg_recycler)
    RecyclerView recyclerView;

    private ThreadsAdapter mNewsAdapter;

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
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
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
        mNewsAdapter = new ThreadsAdapter();
        recyclerView.setAdapter(mNewsAdapter);
    }

    private void getNews() {
        RetrofitUtil.getInstance().api().getThreads()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThreadsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ThreadsBean threadsBean) {
                        mNewsAdapter.setNewData(threadsBean.getResult().getData());
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
