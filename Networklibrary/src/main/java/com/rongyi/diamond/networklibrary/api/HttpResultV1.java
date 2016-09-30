package com.rongyi.diamond.networklibrary.api;

import com.rongyi.diamond.networklibrary.bean.base.BaseMetaV1;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:01
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class HttpResultV1<T> extends BaseMetaV1 {
    public T data;

    public T getData() {
        return data;
    }
}
