package com.rongyi.diamond.networklibrary.progress;

import android.content.Context;
import android.os.Message;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/11/18 下午2:31
 * Description:
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/11/18      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class ProgressSubscriber <T> extends Subscriber<T> implements ProgressCancelListener {
    private SubscriberOnNextListener<T> mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;
    private boolean isDelay = false;

    /**
     * 显示数据加载框 默认可取消，延时显示
     *
     * @param subscriberOnNextListener 数据处理事件
     * @param context                  用于显示Progress的上下文
     */
    public ProgressSubscriber(SubscriberOnNextListener<T> subscriberOnNextListener, Context context) {
        this(subscriberOnNextListener, context, true, false);
    }

    /**
     * 显示数据加载框 默认不会延时显示
     *
     * @param subscriberOnNextListener 数据处理事件
     * @param context                  用于显示Progress的上下文
     * @param cancelable               是否可以取消
     */
    public ProgressSubscriber(SubscriberOnNextListener<T> subscriberOnNextListener, Context context, boolean cancelable) {
        this(subscriberOnNextListener, context, cancelable, false);
    }

    /**
     * 显示数据加载框
     *
     * @param subscriberOnNextListener 数据处理事件
     * @param context                  用于显示Progress的上下文
     * @param cancelable               是否可以取消
     * @param isDelay                  是否延时显示Progress
     */
    public ProgressSubscriber(SubscriberOnNextListener<T> subscriberOnNextListener, Context context, boolean cancelable, boolean isDelay) {
        mSubscriberOnNextListener = subscriberOnNextListener;
        this.isDelay = isDelay;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, cancelable);
    }

    public void setMessage(String message) {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.setMessage(message);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            if (isDelay) {
                showDelayProgressDialog();
            } else {
                mProgressDialogHandler.removeCallbacksAndMessages(null);
                mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
            }
        }
    }

    private void showDelayProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.removeCallbacksAndMessages(null);
            Message message = new Message();
            message.what = ProgressDialogHandler.SHOW_PROGRESS_DIALOG;
            mProgressDialogHandler.sendMessageDelayed(message, 2 * 1000);
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.removeCallbacksAndMessages(null);
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e 错误信息
     */
    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(e);
        }
        dismissProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
        dismissProgressDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
