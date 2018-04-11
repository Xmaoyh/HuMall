package com.maoyihan.www.kobe.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.maoyihan.www.kobe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ListFragment基类
 * Created by MaoYiHan on 2017/9/26 0026
 */

public abstract class BaseListFragment extends BaseFragment {
    @BindView(R.id.newsFg_refresh)
    public SwipeRefreshLayout smartRefreshLayout;
    @BindView(R.id.newsFg_recycler)
    public RecyclerView recyclerView;
    Unbinder unbinder;
    protected BaseQuickAdapter mAdapter;

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.common_single_list, null);
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        setAdapter();
        initRecyclerView();

    }

    @Override
    protected void initData() {
        getData(true);
    }

    @Override
    protected void initListener() {
        AppBarLayout appBarLayout = (AppBarLayout) mActivity.findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    smartRefreshLayout.setEnabled(true);
                } else {
                    smartRefreshLayout.setEnabled(false);
                }
            }
        });
        smartRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(true);
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(false);
            }
        }, recyclerView);
    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    protected abstract void setAdapter();

    protected abstract void getData(boolean isRefresh);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
