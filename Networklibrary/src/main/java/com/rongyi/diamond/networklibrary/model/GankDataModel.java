package com.rongyi.diamond.networklibrary.model;

import com.rongyi.diamond.networklibrary.api.HttpApiMethods;
import com.rongyi.diamond.networklibrary.bean.GankBean;
import com.rongyi.diamond.networklibrary.model.base.BaseDefaultPageModel;
import com.rongyi.diamond.networklibrary.param.GankDataParam;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午11:57
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class GankDataModel extends BaseDefaultPageModel<ArrayList<GankBean>,GankDataParam> {

    public GankDataModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    @Override
    protected void refreshData(Subscriber<ArrayList<GankBean>> subscriber) {
        httpApiMethods.getGankData(subscriber,param.type,param.pageSize,param.pageNo);
    }

    @Override
    protected void loadMoreData(Subscriber<ArrayList<GankBean>> subscriber) {
        param.pageNo++;
        httpApiMethods.getGankData(subscriber,param.type,param.pageSize,param.pageNo);
    }


}