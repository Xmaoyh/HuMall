package com.maoyihan.www.kobe.http;

import com.maoyihan.www.kobe.module.home.bean.NewsBean;
import com.maoyihan.www.kobe.module.home.bean.NewsDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * API请求接口
 * Created by Administrator on 2016/9/15.
 */
public interface IApiAction {

    @GET("https://games.mobileapi.hupu.com/1/7.1.8/nba/getNews?abtest=1&crt=1506477295382&night=0&channel=oppo&sign=ce0887ad9f916686a43f5f78b9b734df&client=864587029303550&time_zone=Asia%2FShanghai&android_id=3fd4ab9ac26c76f1&entrance=-1")
    Observable<NewsBean> getNews();

    @GET("https://games.mobileapi.hupu.com/1/7.1.8/nba/getNewsDetailSchema?crt=1506480948662&night=0&channel=oppo&sign=3cbcdaf332767eb45fbb2b73febc6fd8&nopic=0&time_zone=Asia%2FShanghai&cate_type=news&ft=18&top_ncid=-1&replies=6&client=864587029303550&entrance=1&android_id=3fd4ab9ac26c76f1")
    Observable<NewsDetailBean> getNewsDetail(@Query("nid") String nid);
}
