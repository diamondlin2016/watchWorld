package com.rongyi.diamond.networklibrary.model.base;

import com.rongyi.diamond.baselibiary.app.BaseApplication;
import com.rongyi.diamond.baselibiary.utils.NetworkInfoHelper;
import com.rongyi.diamond.networklibrary.api.HttpApiMethods;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午10:37
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class BaseModel {
    protected HttpApiMethods httpApiMethods;

    public BaseModel(HttpApiMethods httpApiMethods) {
        this.httpApiMethods = httpApiMethods;
    }

    protected boolean isOnline() {//有网络加载网络数据
        return NetworkInfoHelper.isOnline(BaseApplication.getContext());
    }

}
