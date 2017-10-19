package com.maoyihan.www.kobe.module.home.view.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;
import com.maoyihan.www.kobe.module.home.view.activity.NewsDetailActivity;
import com.maoyihan.www.kobe.utils.onMoveAndSwipedListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by MaoYiHan on 2017/9/27 0027
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean.ResultBean.DataBean, BaseViewHolder> implements onMoveAndSwipedListener {
    private View parentView;
    public NewsAdapter() {
        super(R.layout.adapter_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.ResultBean.DataBean item) {
        ImageView ivPic = helper.getView(R.id.newsAda_iv_pic);
        Glide.with(mContext).load(item.getImg()).into(ivPic);
        helper.setText(R.id.newsAda_tv_title, item.getTitle());
        CardView cardView = helper.getView(R.id.newsAda_click);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("nid",item.getNid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        parentView = parent;
        return super.getItemView(layoutResId, parent);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(NewsAdapter.this.getData(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        NewsAdapter.this.getData().remove(position);
        notifyItemRemoved(position);

        Snackbar.make(parentView, "已删除", Snackbar.LENGTH_SHORT)
                .setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }
}
