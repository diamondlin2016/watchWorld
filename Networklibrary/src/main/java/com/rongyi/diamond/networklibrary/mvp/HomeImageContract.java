package com.rongyi.diamond.networklibrary.mvp;

import com.rongyi.diamond.baselibiary.annotation.Implement;
import com.rongyi.diamond.baselibiary.base.mvp.IBaseModel;
import com.rongyi.diamond.baselibiary.base.mvp.IBasePresenter;
import com.rongyi.diamond.baselibiary.base.mvp.IBaseView;
import com.rongyi.diamond.networklibrary.bean.ImageData;
import com.rongyi.diamond.networklibrary.presenter.HomeImagePresenter;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:34
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public interface HomeImageContract {
    interface View extends IBaseView {
        int getImageSize();
     }

    @Implement(HomeImagePresenter.class)
    interface Presenter extends IBasePresenter {
        void getImage(int size);
    }

    interface Model extends IBaseModel {
        void getImage(Subscriber<ImageData> subscriber, int size);
    }

}
