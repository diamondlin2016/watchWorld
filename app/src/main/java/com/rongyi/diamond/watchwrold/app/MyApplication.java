package com.rongyi.diamond.watchwrold.app;


import com.rongyi.diamond.baselibiary.app.BaseApplication;
import com.rongyi.diamond.baselibiary.utils.LogUtils;
import com.rongyi.diamond.baselibiary.utils.NetworkInfoHelper;
import com.rongyi.diamond.networklibrary.api.AppApiService;
import com.rongyi.diamond.networklibrary.api.HttpApiMethods;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 下午8:25
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class MyApplication extends BaseApplication {
    private static final int DEFAULT_TIMEOUT = 15;
    private HttpApiMethods httpApiMethods;

    @Override
    public void onCreate() {
        super.onCreate();
        initPresenter();

        httpApiMethods = buildHttpApiMethods();
    }

    private void initPresenter() {
        //presenter必须在这里注册
//        LogicProxy.getInstance().init(IPresenter.class,IPresenter2.class);
    }

    private HttpApiMethods buildHttpApiMethods() {
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //添加网络请求头信息
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("ua", "Android")
                        .addHeader("deviceType", android.os.Build.MODEL)
                        .addHeader("netWork", NetworkInfoHelper.getCurrentNetType(mContext))
                        .build();
                LogUtils.d("url", request.url().toString());
                return chain.proceed(request);
            }
        };

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String CACHE_CONTROL = "Cache-Control";
                Response originalResponse = chain.proceed(chain.request());
                if (NetworkInfoHelper.isNetworkAvailable(MyApplication.getContext())) {
                    int maxAge = 60; // 在线缓存在1分钟内可读取
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader(CACHE_CONTROL)
                            .header(CACHE_CONTROL, "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader(CACHE_CONTROL)
                            .header(CACHE_CONTROL, "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient httpClientBuilder = new OkHttpClient.Builder()
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .addInterceptor(requestInterceptor)
                .addInterceptor(cacheInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("")
                .build();

        AppApiService appApiService = retrofit.create(AppApiService.class);
        return HttpApiMethods.with(appApiService);
    }

}
