package com.rongyi.diamond.networklibrary.param;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/27 下午4:10
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/27      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class GankDataParam extends BasePageParam{
    public String type;
    public GankDataParam(String type){
        this.type = type;
        init();
    }

}
