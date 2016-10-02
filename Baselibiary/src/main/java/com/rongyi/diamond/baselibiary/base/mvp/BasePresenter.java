package com.rongyi.diamond.baselibiary.base.mvp;

import rx.subscriptions.CompositeSubscription;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/29 下午9:31
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class BasePresenter implements IBasePresenter{
    protected CompositeSubscription mSubscriptions;
    protected final String TAG = getClass().getSimpleName();

    public BasePresenter() {
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }



}