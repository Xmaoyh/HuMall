package com.maoyihan.www.kobe.module.home.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.module.home.bean.ThreadsBean;

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
    }
}
