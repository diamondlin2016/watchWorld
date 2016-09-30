package com.rongyi.diamond.networklibrary.model;

import com.rongyi.diamond.networklibrary.api.HttpApiMethods;
import com.rongyi.diamond.networklibrary.bean.ImageData;
import com.rongyi.diamond.networklibrary.model.base.BaseModel;
import com.rongyi.diamond.networklibrary.mvp.HomeImageContract;

import rx.Subscriber;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:37
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class HomeImageModel extends BaseModel  implements HomeImageContract.Model{

    public HomeImageModel(HttpApiMethods httpApiMethods) {
        super(httpApiMethods);
    }

    @Override
    public void getImage(Subscriber<ImageData> subscriber, int size) {
        httpApiMethods.getImage(subscriber,size);
    }

}
