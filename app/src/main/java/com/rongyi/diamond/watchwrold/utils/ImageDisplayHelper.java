package com.rongyi.diamond.watchwrold.utils;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.rongyi.diamond.baselibiary.utils.FileHelper;
import com.rongyi.diamond.baselibiary.utils.StringHelper;
import com.rongyi.diamond.watchwrold.app.MyApplication;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 下午5:49
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class ImageDisplayHelper {
    /**
     * 显示网络图片
     *
     * @param url          图片地址
     * @param defaultResId 默认资源
     * @param target       显示图片控件
     */
    public static void displayImage(String url, @DrawableRes int defaultResId, ImageView target) {
        displayImage(url, defaultResId, target, null);
    }

    /**
     * 显示网络图片
     *
     * @param url            图片地址
     * @param defaultResId   默认资源
     * @param target         显示图片控件
     * @param transformation 图片变换
     */
    public static void displayImageByTransformation(String url, @DrawableRes int defaultResId, ImageView target, Transformation transformation) {
        if (StringHelper.notEmpty(url)) {
            Picasso.with(MyApplication.getContext()).
                    load(url)
                    .transform(transformation)
                    .placeholder(defaultResId)
                    .error(defaultResId)
                    .into(target);
        } else {
            target.setImageResource(defaultResId);
        }
    }


    /**
     * 显示网络图片
     *
     * @param url          图片地址
     * @param defaultResId 默认资源
     * @param target       显示图片控件
     * @param callback     回调函数
     */
    public static void displayImage(String url, @DrawableRes int defaultResId, ImageView target, Callback callback) {
        if (StringHelper.notEmpty(url)) {
            if (defaultResId == 0) {
                Picasso.with(MyApplication.getContext()).
                        load(url)
                        .into(target, callback);
            } else {
                Picasso.with(MyApplication.getContext()).
                        load(url)
                        .placeholder(defaultResId)
                        .error(defaultResId)
                        .into(target, callback);
            }
        } else {
            target.setImageResource(defaultResId);
        }
    }

    /**
     * 显示本地资源图片
     *
     * @param resId  图片资源ID
     * @param target 显示图片控件
     */
    public static void displayImage(@DrawableRes int resId, ImageView target) {
        displayImage(resId, resId, target, null);
    }

    /**
     * 显示本地资源图片
     *
     * @param resId        图片资源ID
     * @param defaultResId 默认资源
     * @param target       显示图片控件
     */
    public static void displayImage(@DrawableRes int resId, @DrawableRes int defaultResId, ImageView target) {
        displayImage(resId, defaultResId, target, null);
    }

    /**
     * 显示本地资源图片
     *
     * @param resId        图片资源ID
     * @param defaultResId 默认资源
     * @param target       显示图片控件
     * @param callback     回调函数
     */
    public static void displayImage(@DrawableRes int resId, @DrawableRes int defaultResId, ImageView target, Callback callback) {
        if (resId != 0) {
            if (defaultResId == 0) {
                Picasso.with(MyApplication.getContext())
                        .load(resId)
                        .into(target, callback);
            } else {
                Picasso.with(MyApplication.getContext())
                        .load(resId)
                        .placeholder(defaultResId)
                        .error(defaultResId)
                        .into(target, callback);
            }
        } else {
            target.setImageResource(defaultResId);
        }
    }

    /**
     * 显示本地文件资源图片
     *
     * @param path         本地图片地址
     * @param defaultResId 默认资源
     * @param target       显示图片控件
     */
    public static void displayLocalImage(String path, @DrawableRes int defaultResId, ImageView target) {
        displayLocalImage(path, defaultResId, target, null);
    }

    /**
     * 显示本地文件资源图片
     *
     * @param path         本地图片地址
     * @param defaultResId 默认资源
     * @param target       显示图片控件
     * @param callback     回调函数
     */
    public static void displayLocalImage(String path, @DrawableRes int defaultResId, ImageView target, Callback callback) {
        if (FileHelper.isFileExit(path)) {
            if (defaultResId == 0) {
                Picasso.with(MyApplication.getContext())
                        .load(new File(path))
                        .into(target, callback);
            } else {
                Picasso.with(MyApplication.getContext())
                        .load(new File(path))
                        .placeholder(defaultResId)
                        .error(defaultResId)
                        .into(target, callback);
            }
        } else {
            target.setImageResource(defaultResId);
        }
    }
}

