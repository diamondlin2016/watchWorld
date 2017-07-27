package com.rongyi.diamond.networklibrary.mvp;

import com.rongyi.diamond.baselibiary.base.mvp.IBasePresenter;
import com.rongyi.diamond.baselibiary.base.mvp.IBaseView;
import com.rongyi.diamond.networklibrary.bean.GankBean;

import java.util.ArrayList;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午11:40
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public interface GankDataContract {
    interface View extends IBaseView {
        void onDataRefresh(ArrayList<GankBean> list);

        void onDataMore(ArrayList<GankBean> list);

        void onLoadError();

        String getRequestType();
    }

    interface Presenter extends IBasePresenter {
        void getGankData();

        void getMore();

    }

}
