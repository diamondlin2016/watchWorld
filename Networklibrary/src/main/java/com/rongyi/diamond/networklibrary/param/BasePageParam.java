package com.rongyi.diamond.networklibrary.param;

import com.rongyi.diamond.networklibrary.api.AppApiContact;
import com.rongyi.diamond.networklibrary.bean.base.BaseBean;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/27 下午4:06
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/27      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class BasePageParam extends BaseBean {
    public int pageNo;
    public int pageSize;

    public BasePageParam() {
        init();
    }

    protected void init() {
        pageNo = AppApiContact.DEFAULT_CURRENT_PAGE;
        pageSize = AppApiContact.PAGE_SIZE;
    }
}
