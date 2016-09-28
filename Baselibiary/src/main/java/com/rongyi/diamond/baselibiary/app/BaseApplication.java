package com.rongyi.diamond.baselibiary.app;

import android.app.Application;
import android.content.Context;

import com.rongyi.diamond.baselibiary.utils.SharedPreferencesHelper;


/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 上午12:27
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class BaseApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SharedPreferencesHelper.getInstance().Builder(this);
    }


    public static Context getContext() {
        return mContext;
    }
}
