package com.maoyihan.www.kobe.module.home.view.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.BaseBarActivity;
import com.maoyihan.www.kobe.base.DataRepository;
import com.maoyihan.www.kobe.http.RetrofitUtil;
import com.maoyihan.www.kobe.module.home.bean.ThreadDetailBean;
import com.maoyihan.www.kobe.utils.MyImageGetter;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 帖子详情
 * Created by MaoYiHan on 2017/9/29 0029
 */

public class ThreadDetailActivity extends BaseBarActivity {
    @BindView(R.id.threadDetailAct_tv_title)
    TextView tvTitle;
    @BindView(R.id.threadDetailAct_tv_timeAndVisit)
    TextView tvTimeAndVisit;
    @BindView(R.id.threadDetailAct_iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.threadDetailAct_tv_name)
    TextView tvName;
    @BindView(R.id.threadDetailAct_tv_content)
    TextView tvContent;

    private String mTid;

    @Override
    protected int getLayout() {
        return R.layout.activity_thread_detail;
    }

    @Override
    protected void initVariables() {
        mTid = getIntent().getStringExtra("tid");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        DataRepository.getInstance().getThreadDetail(mTid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ThreadDetailBean>() {

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Retro", "onError");
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(ThreadDetailBean bean) {
                        Html.ImageGetter imgGetter = new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String source) {
                                Drawable drawable = null;
                                URL url;
                                try {
                                    url = new URL(source);
                                    drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
                                } catch (Exception e) {
                                    return null;
                                }
                                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                                return drawable;
                            }
                        };
                        ThreadDetailBean.DataBean data = bean.getData();
                        tvTitle.setText(data.getTitle());
                        tvTimeAndVisit.setText(data.getTime() + " 阅读" + data.getVisits());
                        Glide.with(ThreadDetailActivity.this).load(data.getUserImg()).asBitmap().into(new BitmapImageViewTarget(ivAvatar) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(ThreadDetailActivity.this.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                ivAvatar.setImageDrawable(circularBitmapDrawable);
                            }
                        });
                        tvName.setText(data.getUsername());
                        tvContent.setText(Html.fromHtml(data.getContent(), new MyImageGetter(ThreadDetailActivity.this, tvContent), null));
                    }
                });
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

}
