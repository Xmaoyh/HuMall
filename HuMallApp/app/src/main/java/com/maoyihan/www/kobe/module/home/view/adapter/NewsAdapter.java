package com.maoyihan.www.kobe.module.home.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;

/**
 * Created by MaoYiHan on 2017/9/27 0027
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean.ResultBean.DataBean, BaseViewHolder> {
    public NewsAdapter() {
        super(R.layout.adapter_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.ResultBean.DataBean item) {
        ImageView ivPic = helper.getView(R.id.newsAda_iv_pic);
        Glide.with(mContext).load(item.getImg()).into(ivPic);
        helper.setText(R.id.newsAda_tv_title, item.getTitle());

    }
}
