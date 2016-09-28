package com.rongyi.diamond.baselibiary.utils;


import com.orhanobut.logger.Logger;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 下午5:58
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class LogUtils {
    private static boolean DEBUG_D = true;
    private static boolean DEBUG_I = true;
    private static boolean DEBUG_W = true;
    private static boolean DEBUG_E = true;

    /**
     * 设置LOG输出级别
     *
     * @param level 0,1,2,3,4  0-->全部输出
     */
    public static void setLogLevel(int level) {
        DEBUG_D = level >= 1;
        DEBUG_I = level >= 2;
        DEBUG_W = level >= 3;
        DEBUG_E = level >= 4;
    }

    public static void d(String tag, String msg) {
        if (DEBUG_D) {
            Logger.t(tag).d(msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG_I) {
            Logger.t(tag).i(msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG_W) {
            Logger.t(tag).w(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG_E) {
            Logger.t(tag).e(msg);
        }
    }

    public static void json(String tag, String msg) {
        if (DEBUG_D) {
            Logger.t(tag).json(msg);
        }
    }

}
