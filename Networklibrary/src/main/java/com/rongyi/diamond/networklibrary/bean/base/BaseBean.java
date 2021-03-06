package com.rongyi.diamond.networklibrary.bean.base;

import com.google.gson.Gson;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:03
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class BaseBean {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
