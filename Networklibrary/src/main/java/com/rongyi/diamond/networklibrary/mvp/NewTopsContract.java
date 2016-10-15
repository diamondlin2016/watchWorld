package com.rongyi.diamond.networklibrary.mvp;

import com.rongyi.diamond.baselibiary.base.mvp.IBaseModel;
import com.rongyi.diamond.baselibiary.base.mvp.IBasePresenter;
import com.rongyi.diamond.baselibiary.base.mvp.IBaseView;
import com.rongyi.diamond.networklibrary.bean.NewsBean;
import com.rongyi.diamond.networklibrary.bean.NewsList;

import java.util.ArrayList;

import rx.Subscriber;

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
public interface NewTopsContract {
    interface View extends IBaseView {
        void onDataRefresh(ArrayList<NewsBean> list);

        void onDataMore(ArrayList<NewsBean> list);

        void onLoadError();
    }

    //0开始一次+3 +2 +3 +2才会有数据
    interface Presenter extends IBasePresenter {
        void getFirstNewTops();

        void getMoreNewTops();

    }

    interface Model extends IBaseModel {
        void getNews(Subscriber<NewsList> subscriber, int index);

    }

}
