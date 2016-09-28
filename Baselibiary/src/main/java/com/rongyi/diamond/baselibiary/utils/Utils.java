package com.rongyi.diamond.baselibiary.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 下午7:40
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class Utils {
    /**
     * 得到设备屏幕的宽度
     *
     * @param context 上下文
     * @return int
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到设备屏幕的高度
     *
     * @param context 上下文
     * @return int
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 得到设备的密度
     *
     * @param context 上下文
     * @return float
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 把dp转换为px
     *
     * @param context 上下文
     * @param dp      dp值
     * @return int
     */
    public static int dip2px(Context context, float dp) {
        return (int) (dp * getScreenDensity(context) + 0.5);
    }

    /**
     * 把sp转换为px
     *
     * @param context 上下文
     * @param sp      sp
     * @return int
     */
    public static int sp2px(Context context, float sp) {
        return (int) (sp * getScreenDensity(context));
    }

    /**
     * 跳转至拨号界面
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void callDial(Context context, String phoneNumber) {
        if (StringHelper.notEmpty(phoneNumber)) {
            context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
        }
    }

    /**
     * 拨打电话
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    @RequiresPermission("android.permission.CALL_PHONE")
    public static void call(Context context, String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (StringHelper.notEmpty(phoneNumber)) {
            context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
        }
    }

    /**
     * 跳转Activity（intent不需要携带数据）
     *
     * @param context   上下文
     * @param className 下一个页面类名
     */
    public static void intoNextActivity(Context context, Class<?> className) {
        if (className != null) {
            Intent intent = new Intent(context, className);
            context.startActivity(intent);
        }
    }

    /**
     * 获取application中指定的meta-data
     *
     * @param context 上下文
     * @param key     需要取值的KEY
     * @return 如果没有获取成功(没有对应值或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    /**
     * 设置View 是否显示
     *
     * @param view   需要设置的View对象
     * @param isGone 是否隐藏
     * @param <V>    V
     * @return V 当前View
     */
    public static <V extends View> V setGone(V view, boolean isGone) {
        if (view != null) {
            if (isGone) {
                if (View.GONE != view.getVisibility()) {
                    view.setVisibility(View.GONE);
                }
            } else {
                if (View.VISIBLE != view.getVisibility()) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
        return view;
    }

    /**
     * 设置View 是否显示
     *
     * @param view   需要设置的View对象
     * @param isGone 是否隐藏
     * @param <V>    V
     * @return V 当前View
     */
    public static <V extends View> V setInvisible(V view, boolean isGone) {
        if (view != null) {
            if (isGone) {
                if (View.INVISIBLE != view.getVisibility()) {
                    view.setVisibility(View.INVISIBLE);
                }
            } else {
                if (View.VISIBLE != view.getVisibility()) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
        return view;
    }

    /**
     * 多个view隐藏或显示
     *
     * @param gone  true 隐藏；false 显示
     * @param views 多个view对象
     */
    public static void setViewsInvisible(boolean gone, View... views) {
        for (View view : views) {
            setInvisible(view, gone);
        }
    }

    /**
     * 多个view隐藏或显示
     *
     * @param gone  true 隐藏；false 显示
     * @param views 多个view对象
     */
    public static void setViewsGone(boolean gone, View... views) {
        for (View view : views) {
            setGone(view, gone);
        }
    }

    /**
     * 发送短信
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     * @param content     短信内容
     */
    public static void sendSms(Context context, String phoneNumber, String content) {
        if (StringHelper.notEmpty(phoneNumber) && StringHelper.notEmpty(content)) {
            Uri uri = Uri.parse("smsto:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", TextUtils.isEmpty(content) ? "" : content);
            context.startActivity(intent);
        }
    }

    /**
     * 判断APP是否处于前台
     *
     * @param context 上下文
     * @return true 处于前台 false 处于后台
     */
    public static boolean isApplicationBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 安装APK文件
     *
     * @param context 上下文
     * @param file    需要安装的APK文件
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 当前设备是否是手机
     *
     * @param context 上下文
     * @return true 设备为手机
     */
    public static boolean isPhone(Context context) {
        TelephonyManager telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephony.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    /**
     * 获取设备的 IMEI
     *
     * @param context 上下文
     * @return String
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getDeviceIMEI(Context context) {
        String deviceId;
        if (isPhone(context)) {
            TelephonyManager telephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephony.getDeviceId();
        } else {
            deviceId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        return deviceId;
    }

    /**
     * 获取设备的MAC地址
     *
     * @param context 上下文
     * @return String
     */
    public static String getMacAddress(Context context) {
        String macAddress;
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        macAddress = info.getMacAddress();
        if (null == macAddress) {
            return "";
        }
        macAddress = macAddress.replace(":", "");
        return macAddress;
    }

    /**
     * 获取APP的版本号名称
     *
     * @param context 上下文
     * @return String
     */
    public static String getAppVersion(Context context) {
        String version = "6.9.0";
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取APP的版本号
     *
     * @param context 上下文
     * @return int
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;  // 获取软件版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取设备信息
     *
     * @param context 上下文
     * @return Properties
     */
    public static Properties collectDeviceInfo(Context context) {
        Properties mDeviceCrashInfo = new Properties();
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put("VERSION_NAME", pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put("VERSION_CODE", pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("FZUtils", "Error while collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), field.get(null));
            } catch (Exception e) {
                Log.e("FZUtils", "Error while collect crash info", e);
            }
        }

        return mDeviceCrashInfo;
    }

    /**
     * 获取设备信息的的字符串内容
     *
     * @param context 上下文
     * @return String
     */
    public static String collectDeviceInfoStr(Context context) {
        Properties prop = collectDeviceInfo(context);
        Set deviceInfos = prop.keySet();
        StringBuilder deviceInfoStr = new StringBuilder("{\n");
        for (Object item : deviceInfos) {
            deviceInfoStr.append("\t\t\t").append(item).append(":").append(prop.get(item)).append(", \n");
        }
        deviceInfoStr.append("}");
        return deviceInfoStr.toString();
    }

    /**
     * 隐藏软键盘
     *
     * @param activity Activity
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputManger = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context 上下文
     * @param edit    编辑框
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void hideSoftInput(Context context, EditText edit) {
        edit.clearFocus();
        InputMethodManager inputManger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManger.hideSoftInputFromWindow(edit.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     *
     * @param context 上下文
     * @param edit    编辑框
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void showSoftInput(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(edit, 0);
    }

    /**
     * 动态显示或者是隐藏软键盘
     *
     * @param context 上下文
     * @param edit    编辑框
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void toggleSoftInput(Context context, EditText edit) {
        edit.setFocusable(true);
        edit.setFocusableInTouchMode(true);
        edit.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * 获取容易逛验证码
     *
     * @param content 短信内容
     * @return 验证码
     */
    public static String getRongYiCode(String content) {
        String code = null;
        if (StringHelper.notEmpty(content)) {
            int index = content.lastIndexOf("容易逛");
            if (index != -1) {//是容易网短信
                code = getNumbers(content);
            }
        }
        return code;
    }

    //截取数字
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    private static final double EARTH_RADIUS = 6378137.0;

    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static String getDistanceDisplay(double longitude1, double latitude1, double longitude2, double latitude2) {
        if (longitude1 > 0.01 && latitude1 > 0.01 && longitude2 > 0.01 && latitude2 > 0.01) {
            double distance = getDistance(longitude1, latitude1, longitude2, latitude2);
            distance = Math.floor(distance);
            DecimalFormat df = new DecimalFormat("#");
            if (distance <= 1000) {
                return df.format(distance) + "m";
            } else {
                distance = Math.floor(distance / 1000);
                return df.format(distance) + "km";
            }
        } else {
            return "";
        }
    }


}

