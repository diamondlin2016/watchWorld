package com.rongyi.diamond.networklibrary.bean.base;

import com.rongyi.diamond.baselibiary.utils.StringHelper;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:02
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class BaseMetaGank extends BaseBean {
    public boolean error;
    public String msg;

//    public boolean isSuccess() {
//        return result == AppApiContact.STATUS_NET_SUCCESS;
//    }
//
    public String getMsg() {
        return (StringHelper.isEmpty(msg)) ? "" : msg;
    }

}
