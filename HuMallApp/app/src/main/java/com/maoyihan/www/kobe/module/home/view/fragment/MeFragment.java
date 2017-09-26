package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;

/**
 * 商品
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class MeFragment extends BaseFragment {

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    protected void initView(View view) {

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
}
