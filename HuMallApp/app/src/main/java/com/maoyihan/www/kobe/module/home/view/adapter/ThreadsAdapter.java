package com.maoyihan.www.kobe.module.home.view.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.module.home.bean.ThreadsBean;
import com.maoyihan.www.kobe.module.home.view.activity.NewsDetailActivity;
import com.maoyihan.www.kobe.module.home.view.activity.ThreadDetailActivity;

/**
 * Created by MaoYiHan on 2017/9/27 0027
 */

public class ThreadsAdapter extends BaseQuickAdapter<ThreadsBean.ResultBean.DataBean, BaseViewHolder> {
    public ThreadsAdapter() {
        super(R.layout.adapter_threads);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThreadsBean.ResultBean.DataBean item) {
        helper.setText(R.id.tvTitle, item.getTitle());
        helper.setText(R.id.tvLight, item.getLightReply() + "");
        helper.setText(R.id.tvReply, item.getReplies());
        helper.setText(R.id.tvSingleTime,item.getTime());
        CardView cardView = helper.getView(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ThreadDetailActivity.class);
                intent.putExtra("tid",item.getTid());
                mContext.startActivity(intent);
            }
        });
    }
}
