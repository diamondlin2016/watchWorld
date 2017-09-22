package com.rongyi.diamond.watchwrold.presenter;

import android.content.Context;

import com.rongyi.diamond.baselibiary.base.mvp.BasePresenter;
import com.rongyi.diamond.baselibiary.utils.ToastHelper;
import com.rongyi.diamond.networklibrary.model.DownImageModel;
import com.rongyi.diamond.networklibrary.mvp.PhotoContract;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 下午5:28
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class PhotoPresenter extends BasePresenter implements PhotoContract.Presenter {

    private final DownImageModel mDownImageModel;

    public PhotoPresenter() {
        mDownImageModel = new DownImageModel();
    }


    @Override
    public void saveImage(Context context, String url) {
        mSubscriptions.clear();
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastHelper.showToastMessage(e.getMessage());
            }

            @Override
            public void onNext(String imageData) {
                ToastHelper.showToastMessage("保存成功" + imageData);
            }
        };
        mDownImageModel.saveImage(subscriber, context, url);
        mSubscriptions.add(subscriber);

    }

    @Override
    public void shareImage(Context context, String title, String msg, String url) {
        ToastHelper.showToastMessage("分享成功");
    }
}
