package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;
import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;
import com.maoyihan.www.kobe.module.home.view.adapter.NewsAdapter;
import com.maoyihan.www.kobe.utils.ItemTouchHelperCallback;

import org.reactivestreams.Subscriber;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 视频
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class VideoFragment extends BaseFragment {
    @Bind(R.id.newsFg_refresh)
    SwipeRefreshLayout smartRefreshLayout;
    @Bind(R.id.newsFg_recycler)
    RecyclerView recyclerView;

    private NewsAdapter mNewsAdapter;

    public static VideoFragment newInstance() {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
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
        mNewsAdapter = new NewsAdapter();
        recyclerView.setAdapter(mNewsAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mNewsAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void getNews() {
        RetrofitUtil.getInstance().api().getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(@NonNull NewsBean newsBean) throws Exception {
                        mNewsAdapter.setNewData(newsBean.getResult().getData());
                        smartRefreshLayout.setRefreshing(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        smartRefreshLayout.setRefreshing(false);
                    }
                });
//                .subscribe(new Subscriber<NewsBean>() {
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
//                    public void onNext(NewsBean newsBean) {
//                        mNewsAdapter.setNewData(newsBean.getResult().getData());
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
