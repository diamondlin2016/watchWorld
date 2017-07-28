package com.rongyi.diamond.baselibiary.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.rongyi.diamond.baselibiary.utils.SharedPreferencesHelper;
import com.squareup.picasso.Picasso;


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
    protected static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesHelper.getInstance().Builder(this);
        mContext = getApplicationContext();
        setUpLog();
        Picasso.with(mContext);
    }

    public static Context getContext() {
        return mContext;
    }

    protected void setUpLog() {
        Logger.init("diamond_lin")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional
    }

}
