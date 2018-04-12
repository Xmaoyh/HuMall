package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 商品
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class GoodsFragment extends BaseFragment {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.editTextName)
    EditText edtTName;
    @BindView(R.id.editTextPsw)
    EditText edtTPsw;
    Unbinder unbinder;

    private Observable<Long> mVerifyCodeObservable;
    private Disposable mDisposable;
    private static int SECOND = 20;

    public static GoodsFragment newInstance() {
        Bundle args = new Bundle();
        GoodsFragment fragment = new GoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_goods, null);
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        Observable<CharSequence> observableName = RxTextView.textChanges(edtTName);
        Observable<CharSequence> observablePsw = RxTextView.textChanges(edtTPsw);
        Observable.combineLatest(observableName, observablePsw, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(@NonNull CharSequence charSequence, @NonNull CharSequence charSequence2) throws Exception {
                return !TextUtils.isEmpty(edtTName.getText().toString()) && !TextUtils.isEmpty(edtTPsw.getText().toString());
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                RxView.enabled(button).accept(aBoolean);
            }
        });
        //首先通过 RxView.clicks() 绑定并转换成一个倒计时的 Observable 观察者对象。
        mVerifyCodeObservable = RxView.clicks(button)
                .throttleFirst(SECOND, TimeUnit.SECONDS)
                .flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(@NonNull Object o) throws Exception {
                        //更新发送按钮的状态并初始化显现倒计时文字
                        RxView.enabled(button).accept(false);
                        RxTextView.text(button).accept("剩余 " + SECOND + " 秒");
                        //在实际操作中可以在此发送获取网络的请求

                        //返回 N 秒内的倒计时观察者对象。
                        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.io()).take(SECOND);

                    }
                })
                //将递增数字替换成递减的倒计时数字
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return SECOND - (aLong + 1);
                    }
                })
                //切换到 Android 的主线程
                .observeOn(AndroidSchedulers.mainThread());
        //设置作为倒计时提示的 Consumer 被观察者对象。
        Consumer<Long> mConsumerCountTime = new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                //显示剩余时长。当倒计时为 0 时，还原 btn 按钮.
                if (aLong == 0) {
                    RxView.enabled(button).accept(true);
                    RxTextView.text(button).accept("获取验证码");
                } else {
                    RxTextView.text(button).accept("剩余 " + aLong + " 秒");
                }
            }
        };
        //订阅点击事件:
        mDisposable = mVerifyCodeObservable.subscribe(mConsumerCountTime);
        //停止倒计时，但依然可以再次点击。
        RxView.clicks(button).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (mDisposable != null && !mDisposable.isDisposed()) {
                    //停止倒计时
                    mDisposable.dispose();
                    //重新订阅
                    mDisposable = mVerifyCodeObservable.subscribe(mConsumerCountTime);
                    //按钮可点击
                    RxView.enabled(button).accept(true);
                    RxTextView.text(button).accept("获取验证码");
                }
            }
        });


//        mVerifyCodeObservable.subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).take(SECOND).subscribe(new Subscriber<Long>() {
//                    @Override
//                    public void onCompleted() {
//                        RxTextView.text(button).call("获取验证码");
//                        RxView.enabled(button).call(true);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        RxTextView.text(button).call("剩余" + (SECOND - aLong) + "秒");
//                    }
//                });
//            }
//        });

    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mVerifyCodeObservable.unsubscribeOn(AndroidSchedulers.mainThread());
        if (mDisposable != null) {
            mDisposable.dispose();
        }

    }

}
