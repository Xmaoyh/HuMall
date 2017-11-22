package com.maoyihan.www.kobe.module.home.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.module.home.bean.PhotoArticleBean;

/**
 * Created by MaoYiHan on 2017/9/27 0027
 */

public class OldPicAdapter extends BaseQuickAdapter<PhotoArticleBean.DataBean, BaseViewHolder> {
    private View parentView;
    public OldPicAdapter() {
        super(R.layout.adapter_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoArticleBean.DataBean item) {
        ImageView ivPic = helper.getView(R.id.newsAda_iv_pic);
        Glide.with(mContext).load(item.getImage_url()).into(ivPic);
        helper.setText(R.id.newsAda_tv_title, item.getTitle());
        CardView cardView = helper.getView(R.id.newsAda_click);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, NewsDetailActivity.class);
//                intent.putExtra("nid",item);
//                mContext.startActivity(intent);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.anim_recycler_item_show);
        cardView.startAnimation(animation);
    }

    @Override
    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        parentView = parent;
        return super.getItemView(layoutResId, parent);
    }

}
