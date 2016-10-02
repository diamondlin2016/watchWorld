package com.rongyi.diamond.networklibrary.api;

import com.rongyi.diamond.baselibiary.app.BaseApplication;
import com.rongyi.diamond.baselibiary.utils.NetworkInfoHelper;
import com.rongyi.diamond.networklibrary.bean.ImageData;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午12:29
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class HttpApiMethods {
    static volatile HttpApiMethods singleton = null;
    private AppApiService appApiService;
    public static final String NETWORK_ERROR_MSG = "没有网络连接,请打开你的网络连接";
    public static final String DEFAULT_ERROR_MSG = "网络不给力,请稍后重试~";

    //构造方法私有
    private HttpApiMethods(AppApiService appApiService) {
        this.appApiService = appApiService;
    }

    //在访问HttpMethods时创建单例
    public static HttpApiMethods with(AppApiService appApiService) {
        if (singleton == null) {
            synchronized (HttpApiMethods.class) {
                if (singleton == null) {
                    singleton = new Builder(appApiService).build();
                }
            }
        }
        return singleton;
    }

    public static class Builder {
        private AppApiService appApiService;

        public Builder(AppApiService appApiService) {
            if (appApiService == null) {
                throw new IllegalArgumentException("AppApiService must not be null.");
            }
            this.appApiService = appApiService;
        }

        public HttpApiMethods build() {
            return new HttpApiMethods(appApiService);
        }
    }

    private static final Observable.Transformer ioTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    private void toSubscribe(Observable<?> observable, Subscriber subscriber) {
        if (checkNetwork(subscriber)) {
            observable.compose(applyIoSchedulers())
                    .subscribe(subscriber);
        }
    }
    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return (Observable.Transformer<T, T>) ioTransformer;
    }

    /**
     * 检查是否有网络连接
     *
     * @param subscriber 订阅事件
     * @return true 有网络连接
     */
    protected boolean checkNetwork(Subscriber subscriber) {
        if (!NetworkInfoHelper.isOnline(BaseApplication.getContext())) {
            subscriber.onError(new ApiException(NETWORK_ERROR_MSG));
            return false;
        }
        return true;
    }


    /**
     * 获取首页图片
     *
     * @param size 获取图片张数
     */
    public void getImage(Subscriber<ImageData> subscriber, int size) {
        toSubscribe(appApiService.getImage(size)
                        .compose(new RedirectResponseV1Transformer<ImageData>()),
                subscriber);
    }




}
