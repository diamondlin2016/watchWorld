package com.rongyi.diamond.networklibrary.model;

import com.rongyi.diamond.networklibrary.api.HttpApiMethods;
import com.rongyi.diamond.networklibrary.bean.NewsList;
import com.rongyi.diamond.networklibrary.model.base.BaseModel;
import com.rongyi.diamond.networklibrary.mvp.NewTopsContract;

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
public class NewTopsModel extends BaseModel implements NewTopsContract.Model{

    public NewTopsModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    @Override
    public void getNews(Subscriber<NewsList> subscriber, int index) {
        httpApiMethods.getNews(subscriber,index);
    }

}