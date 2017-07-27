package com.rongyi.diamond.networklibrary.api;

import com.rongyi.diamond.baselibiary.utils.LogUtils;
import com.rongyi.diamond.baselibiary.utils.StringHelper;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:29
 * Description:网络请求数据过滤
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class RedirectResponseV2Transformer<T> implements Observable.Transformer<HttpResultGank<T>, T> {
    @Override
    public Observable<T> call(Observable<HttpResultGank<T>> httpResultV2Observable) {
        return httpResultV2Observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .lift(new Observable.Operator<T, HttpResultGank<T>>() {
                    @Override
                    public Subscriber<? super HttpResultGank<T>> call(
                            final Subscriber<? super T> subscriber) {
                        return new Subscriber<HttpResultGank<T>>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(HttpResultGank<T> httpResult) {
                                LogUtils.json("http", httpResult.toJson());
                                if (!httpResult.error) {
                                    subscriber.onNext(httpResult.getData());
                                } else {
                                    String msg = HttpApiMethods.DEFAULT_ERROR_MSG;
                                    if (StringHelper.notEmpty(httpResult.getMsg())) {
                                        msg = httpResult.getMsg();
                                    }
                                    subscriber.onError(new ApiException(msg));
                                }
                            }
                        };
                    }
                });
    }
}
