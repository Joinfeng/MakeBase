package com.mark.common;

import android.app.Application;
import android.os.Debug;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mark.common.util.ConUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class BaseApp extends Application {

    private static BaseApp sInstance;

    public static synchronized BaseApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*全局化application*/
        sInstance = this;

        /*引用了ARouter官方git文档中的注释*/
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);// 尽可能早，推荐在Application中初始化

        /*将common中的工具类初始化：context*/
        ConUtils.init(this);

        /*OkHttpUtils需要OkHttpClient实例进行初始化*/
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                //.addInterceptor(new LoggerInterceptor("Tag"))
                .connectTimeout(100000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他设置
                .build();
        OkHttpUtils.initClient(okhttpClient);
    }
}
