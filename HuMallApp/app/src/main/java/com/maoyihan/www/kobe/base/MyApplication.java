package com.maoyihan.www.kobe.base;

import android.support.multidex.MultiDexApplication;

import com.maoyihan.www.kobe.db.AppDatabase;
import com.maoyihan.www.kobe.db.AppExecutors;

/**
 * 应用程序实体
 * Created by Administrator on 2016/9/15.
 */
public class MyApplication extends MultiDexApplication {
    private static MyApplication app;
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mAppExecutors = new AppExecutors();
    }

    public static MyApplication getInstance() {
        return app;
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public AppExecutors getAppExecutors(){
        return  mAppExecutors;
    }

//    public DataRepository getRepository() {
//        return DataRepository.getInstance(getDatabase());
//    }
}
