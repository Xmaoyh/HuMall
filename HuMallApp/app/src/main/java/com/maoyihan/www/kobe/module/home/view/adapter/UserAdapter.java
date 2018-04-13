package com.maoyihan.www.kobe.module.home.view.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.db.entity.UserEntity;
import com.maoyihan.www.kobe.module.home.bean.ThreadsBean;
import com.maoyihan.www.kobe.module.home.view.activity.ThreadDetailActivity;
import com.maoyihan.www.kobe.utils.onMoveAndSwipedListener;

/**
 * Created by MaoYiHan on 2017/9/27 0027
 */

public class UserAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> implements onMoveAndSwipedListener {
    private View parentView;
    private OnDeleteListener mOnDeleteListener;
    public UserAdapter() {
        super(R.layout.adapter_threads);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {
        helper.setText(R.id.tvTitle, item.getName());
        helper.setText(R.id.tvLight, item.getUid() + "");
//        helper.setText(R.id.tvReply, item.getReplies());
//        helper.setText(R.id.tvSingleTime,item.getTime());
    }

    @Override
    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        parentView = parent;
        return super.getItemView(layoutResId, parent);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        if(mOnDeleteListener != null){
            mOnDeleteListener.onDelete(this.getData().get(position)) ;
        }
        UserAdapter.this.getData().remove(position);
        notifyItemRemoved(position);
//        Snackbar.make(parentView, "已删除", Snackbar.LENGTH_SHORT)
//                .setAction("撤销", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                }).show();
    }

    public interface OnDeleteListener{
        void onDelete(UserEntity userEntity);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }
}
