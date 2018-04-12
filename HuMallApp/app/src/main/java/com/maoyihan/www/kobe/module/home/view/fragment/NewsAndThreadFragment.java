package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 新闻和帖子
 * Created by MaoYiHan on 2017/9/27 0027
 */

public class NewsAndThreadFragment extends BaseFragment {
    @BindView(R.id.newsThreadFg_tl)
    TabLayout tabLayout;
    @BindView(R.id.newsThreadFg_viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    private List<Fragment> mFragmentList;
    private String[] mStrings = {"新闻", "帖子"};

    public static NewsAndThreadFragment newInstance() {
        Bundle args = new Bundle();
        NewsAndThreadFragment fragment = new NewsAndThreadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_news_thread, null);
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        initViewPager();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initViewPager() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(NewsFragment.newInstance());
        mFragmentList.add(ThreadsFragment.newInstance());
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mStrings[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}
