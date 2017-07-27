package com.rongyi.diamond.networklibrary.api;

import com.rongyi.diamond.networklibrary.bean.base.BaseMetaGank;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/27 下午3:25
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/27      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class HttpResultGank <T> extends BaseMetaGank {
    public T results;

    public T getData() {
        return results;
    }
}
