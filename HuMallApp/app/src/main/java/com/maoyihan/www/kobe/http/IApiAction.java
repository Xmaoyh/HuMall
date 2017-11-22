package com.maoyihan.www.kobe.http;

import com.maoyihan.www.kobe.config.AppConstant;
import com.maoyihan.www.kobe.module.home.bean.NewsBean;
import com.maoyihan.www.kobe.module.home.bean.NewsDetailBean;
import com.maoyihan.www.kobe.module.home.bean.PhotoArticleBean;
import com.maoyihan.www.kobe.module.home.bean.ThreadDetailBean;
import com.maoyihan.www.kobe.module.home.bean.ThreadsBean;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;



/**
 * API请求接口
 * Created by Administrator on 2016/9/15.
 */
public interface IApiAction {

    @GET("https://games.mobileapi.hupu.com/1/7.1.8/nba/getNews?abtest=1&crt=1506477295382&night=0&channel=oppo&sign=ce0887ad9f916686a43f5f78b9b734df&client=864587029303550&time_zone=Asia%2FShanghai&android_id=3fd4ab9ac26c76f1&entrance=-1")
    Observable<NewsBean> getNews();

    @GET("http://games.mobileapi.hupu.com/1/7.1.8/news/createNewsDetailH5?offline=json&leaguesEn=nba&entrance=1&night=0&nopic=0&time_zone=Asia%2FShanghai&client=864587029303550&webp=1")
    Observable<NewsDetailBean> getNewsDetail(@Query("nid") String nid);

    @GET("https://bbs.mobileapi.hupu.com/1/7.1.8/recommend/getThreadsList?crt=1506504852494&night=0&lastTid=0&sign=ab04c4725495c41bdd29f5e76ddafb44&client=864587029303550&stamp=1506504601&isHome=1&time_zone=Asia%2FShanghai&android_id=3fd4ab9ac26c76f1&additionTid=0&unfollowTid=")
    Observable<ThreadsBean> getThreads();

    @GET("http://bbs.mobileapi.hupu.com/1/7.1.8/threads/getsThreadInfo?offline=json&fid=34&nopic=0&night=0&page=1&nps=-999&client=864587029303550&webp=1")
    Observable<ThreadDetailBean> getThreadDetail(@Query("tid") String tid);

    /**
     * 获取图片标题等信息
     * http://www.toutiao.com/api/article/feed/?category=类型&as=A115C8457F69B85&cp=585F294B8845EE1&_=时间&count=30
     */
    @GET("http://www.toutiao.com/api/pc/feed/?as=A115C8457F69B85&cp=585F294B8845EE1")
    Observable<PhotoArticleBean> getPhotoArticle(
            @Query("category") String category,
            @Query("max_behot_time") String time);

    /**
     * 获取图片内容HTML内容
     * 抓取 url 较复杂
     * 详情查看 {@linkplain com.meiji.toutiao.module.photo.content.PhotoContentPresenter#doLoadData(String...)}
     */
    @GET()
    @Headers("User-Agent:" + AppConstant.USER_AGENT_PC)
    Call<ResponseBody> getPhotoContentHTML(@Url String url);


}
