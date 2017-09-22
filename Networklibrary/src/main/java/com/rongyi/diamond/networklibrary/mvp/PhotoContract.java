package com.rongyi.diamond.networklibrary.mvp;

import android.content.Context;

import com.rongyi.diamond.baselibiary.base.mvp.IBaseModel;
import com.rongyi.diamond.baselibiary.base.mvp.IBasePresenter;
import com.rongyi.diamond.baselibiary.base.mvp.IBaseView;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 下午5:27
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public interface PhotoContract {

    interface View extends IBaseView{
        void onSaveSuccess();
        void onShareSuccess();
        void error(String msg);
    }
    interface Presenter extends IBasePresenter {
        void saveImage(Context context, String url);

        void shareImage(Context context,String title,String msg,String url);
    }

    interface Model extends IBaseModel {
        void saveImage(Subscriber<String> subscriber, Context context, String url);
    }
}
