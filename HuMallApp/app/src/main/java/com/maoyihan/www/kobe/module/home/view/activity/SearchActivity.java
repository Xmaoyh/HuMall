package com.maoyihan.www.kobe.module.home.view.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class SearchActivity extends BaseBarActivity {
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //后退按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void loadData() {
        handleIntent(getIntent());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //通过某种方法，根据请求检索你的数据
            tvResult.setText(query);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
