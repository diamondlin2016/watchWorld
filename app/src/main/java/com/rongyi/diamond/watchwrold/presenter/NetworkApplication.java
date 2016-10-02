package com.rongyi.diamond.watchwrold.presenter;

import com.rongyi.diamond.baselibiary.app.BaseApplication;
import com.rongyi.diamond.baselibiary.utils.LogUtils;
import com.rongyi.diamond.baselibiary.utils.NetworkInfoHelper;
import com.rongyi.diamond.networklibrary.api.AppApiService;
import com.rongyi.diamond.networklibrary.api.HttpApiMethods;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
 * Date:      16/9/30 下午1:37
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class NetworkApplication extends BaseApplication {
    private static final int DEFAULT_TIMEOUT = 15;
    private HttpApiMethods httpApiMethods;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        httpApiMethods = buildHttpApiMethods();
    }

    private HttpApiMethods buildHttpApiMethods() {

        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
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
                if (NetworkInfoHelper.isNetworkAvailable(NetworkApplication.getContext())) {
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

        File httpCacheDirectory = new File(NetworkApplication.getContext().getCacheDir(), "RongYiCache");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(cacheInterceptor)
                .addInterceptor(requestInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .build();

        AppApiService service = new Retrofit.Builder()
                .baseUrl("http://http://www.google.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AppApiService.class);
        return HttpApiMethods.with(service);
    }

    public HttpApiMethods getHttpApiMethods() {
        return httpApiMethods;
    }

    public static NetworkApplication getContext() {
        return (NetworkApplication) mContext;
    }

}
