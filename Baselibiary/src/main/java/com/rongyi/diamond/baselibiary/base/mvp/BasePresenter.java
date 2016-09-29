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
public class BasePresenter<T extends BaseView> implements IBasePresenter<T>{
    protected CompositeSubscription mSubscriptions;
    protected final String TAG = getClass().getSimpleName();
    private T mView;

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

    @Override
    public void attachView(T mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isViewBind() {
        return mView != null;
    }


    public T getView() {
        return mView;
    }

    public void checkViewAttached() {
        if (!isViewBind()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" +
                    " requesting data to the Presenter");
        }
    }

}