package com.rongyi.diamond.baselibiary.base.mvp;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/11/17 上午11:03
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/11/17      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public interface IBasePageModel<T, D> {
    void loadList(Subscriber<T> subscriber, boolean isRefresh, D param);
}
