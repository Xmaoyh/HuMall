package com.maoyihan.www.kobe.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.maoyihan.www.kobe.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 网页
 * Created by Administrator on 2017/8/8 0008.
 */

public class WebActivity extends BaseBarActivity {
    @Bind(R.id.webAct_webView)
    WebView webView;

    private String mUrl;
    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initVariables() {
        mUrl = getIntent().getStringExtra("url");
        if (!mUrl.contains("toutiao")) {
            mUrl = "http://toutiao.com" + mUrl;
        }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.loadUrl(mUrl);
    }

    @Override
    protected void loadData() {

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
