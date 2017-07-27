package com.rongyi.diamond.networklibrary.model.base;

import com.rongyi.diamond.baselibiary.base.mvp.IBasePageModel;
import com.rongyi.diamond.networklibrary.api.ApiException;
import com.rongyi.diamond.networklibrary.api.HttpApiMethods;
import com.rongyi.diamond.networklibrary.param.BasePageParam;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/27 下午3:53
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/27      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public abstract class BaseDefaultPageModel <T, D extends BasePageParam> extends BasePageModel<T> implements IBasePageModel<T, D> {
    protected D param;

    public BaseDefaultPageModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    @Override
    public void loadList(Subscriber<T> subscriber, boolean isRefresh, D param) {
        if (param != null)
            this.param = param;
        if (isRefresh) {//刷新数据
            if (isOnline()) {//有网络加载网络数据
                refreshData(subscriber);
            } else {//没有网络，加载缓存数据
                subscriber.onError(new ApiException(HttpApiMethods.NETWORK_ERROR_MSG));
            }
        } else {//加载更多数据
            loadMoreData(subscriber);
        }
    }
}
