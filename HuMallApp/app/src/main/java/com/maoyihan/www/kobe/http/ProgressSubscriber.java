package com.maoyihan.www.kobe.http;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseActivity;
import com.maoyihan.www.kobe.base.MyApplication;
import com.maoyihan.www.kobe.utils.ToastUtils;

import rx.Subscriber;

/**
 * 请求开始显示progress，请求结束关闭progress
 * Created by Administrator on 2017/8/10 0010.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> {
    private BaseActivity mActivity;

    public ProgressSubscriber(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity.showLoadingView();
    }

    @Override
    public void onCompleted() {
        mActivity.hideLoadingView();
    }

    @Override
    public void onError(Throwable e) {
        mActivity.hideLoadingView();
        ToastUtils.showShort(MyApplication.getInstance(), MyApplication.getInstance().getString(R.string.network_on_error) + e.toString());
    }

    @Override
    public abstract void onNext(T t);

}
