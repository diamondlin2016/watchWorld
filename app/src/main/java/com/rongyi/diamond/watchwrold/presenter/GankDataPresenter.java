package com.rongyi.diamond.watchwrold.presenter;

import android.support.annotation.StringDef;

import com.rongyi.diamond.baselibiary.base.mvp.BasePresenter;
import com.rongyi.diamond.networklibrary.app.NetworkApplication;
import com.rongyi.diamond.networklibrary.bean.GankBean;
import com.rongyi.diamond.networklibrary.model.GankDataModel;
import com.rongyi.diamond.networklibrary.mvp.GankDataContract;
import com.rongyi.diamond.networklibrary.param.GankDataParam;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午11:59
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class GankDataPresenter extends BasePresenter implements GankDataContract.Presenter {

    private final GankDataContract.View mView;
    private GankDataModel mGankDataModel;
    //all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    public static final String TYPE_ALL = "all";//all
    public static final String TYPE_ANDROID = "android";//
    public static final String TYPE_VIDEO = "休息视频";//
    public static final String TYPE_MEIZI = "福利";//
    public static final String TYPE_EXTOR = "拓展资源";//
    public static final String TYPE_H5 = "前端";//
    public static final String TYPE_RECOMMEND = "瞎推荐";//
    public static final String TYPE_APP = "App";//

    @StringDef({TYPE_ALL, TYPE_ANDROID, TYPE_VIDEO, TYPE_MEIZI, TYPE_EXTOR, TYPE_H5, TYPE_RECOMMEND, TYPE_APP})
    public @interface GankDataType {
    }


    public GankDataPresenter(GankDataContract.View view) {
        mView = view;
    }


    @Override
    public void getGankData() {
        if (mGankDataModel == null) {
            mGankDataModel = new GankDataModel(NetworkApplication.getContext().getHttpApiMethods());
        }
        mSubscriptions.clear();
        Subscriber<ArrayList<GankBean>> subscriber = new Subscriber<ArrayList<GankBean>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.onLoadError();
            }

            @Override
            public void onNext(ArrayList<GankBean> list) {
                mView.onDataRefresh(list);
            }
        };
        mGankDataModel.loadList(subscriber, true, new GankDataParam(mView.getRequestType()));
        mSubscriptions.add(subscriber);
    }

    @Override
    public void getMore() {
        mSubscriptions.clear();
        Subscriber<ArrayList<GankBean>> subscriber = new Subscriber<ArrayList<GankBean>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mView.onLoadError();
            }

            @Override
            public void onNext(ArrayList<GankBean> list) {
                mView.onDataMore(list);
            }
        };
        mGankDataModel.loadList(subscriber, false, null);
        mSubscriptions.add(subscriber);
    }


}
