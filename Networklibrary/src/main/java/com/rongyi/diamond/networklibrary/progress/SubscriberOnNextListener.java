package com.rongyi.diamond.networklibrary.progress;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/11/18 下午2:33
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/11/18      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public interface SubscriberOnNextListener <T> {
    void onNext(T t);

    void onError(Throwable e);
}
