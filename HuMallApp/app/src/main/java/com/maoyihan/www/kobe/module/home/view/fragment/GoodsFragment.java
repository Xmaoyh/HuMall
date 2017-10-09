package com.maoyihan.www.kobe.module.home.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * 商品
 * Created by MaoYiHan on 2017/9/26 0026
 */

public class GoodsFragment extends BaseFragment {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.editTextName)
    EditText edtTName;
    @Bind(R.id.editTextPsw)
    EditText edtTPsw;

    private Observable<Void> verifyCodeObservable;
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
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        Observable<CharSequence> observableName = RxTextView.textChanges(edtTName);
        Observable<CharSequence> observablePsw = RxTextView.textChanges(edtTPsw);
        Observable.combineLatest(observableName, observablePsw, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence, CharSequence charSequence2) {
                return !TextUtils.isEmpty(edtTName.getText().toString()) && !TextUtils.isEmpty(edtTPsw.getText().toString());
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(button).call(aBoolean);
            }
        });
        verifyCodeObservable = RxView.clicks(button)
                .throttleFirst(SECOND, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        RxView.enabled(button).call(false);
                    }
                });
        verifyCodeObservable.subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).take(SECOND).subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        RxTextView.text(button).call("获取验证码");
                        RxView.enabled(button).call(true);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        RxTextView.text(button).call("剩余" + (SECOND - aLong) + "秒");
                    }
                });
            }
        });



    }

    @Override
    protected void handleBundle(Bundle savedInstanceState) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        verifyCodeObservable.unsubscribeOn(AndroidSchedulers.mainThread());
    }

}
