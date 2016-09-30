package com.rongyi.diamond.networklibrary.model.base;

import com.rongyi.diamond.networklibrary.api.AppApiContact;
import com.rongyi.diamond.networklibrary.api.HttpApiMethods;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午10:39
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BasePageModel<T> extends BaseModel {
    protected int currentPage = AppApiContact.DEFAULT_CURRENT_PAGE;

    public BasePageModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isRefresh() {
        return currentPage == AppApiContact.DEFAULT_CURRENT_PAGE;
    }

    protected abstract void refreshData(Subscriber<T> subscriber);//加载最新数据

    protected abstract void loadMoreData(Subscriber<T> subscriber);//加载更多数据

}
