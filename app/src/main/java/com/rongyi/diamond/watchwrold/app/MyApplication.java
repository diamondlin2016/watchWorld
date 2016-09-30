package com.rongyi.diamond.watchwrold.app;


import com.rongyi.diamond.baselibiary.base.mvp.LogicProxy;
import com.rongyi.diamond.networklibrary.NetworkApplication;
import com.rongyi.diamond.networklibrary.mvp.HomeImageContract;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 下午8:25
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class MyApplication extends NetworkApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initPresenter();
        mContext = getApplicationContext();
    }

    private void initPresenter() {
        //presenter必须在这里注册
        LogicProxy.getInstance().init(HomeImageContract.Presenter.class);
    }



    public static MyApplication getContext() {
        return (MyApplication)mContext;
    }

}
