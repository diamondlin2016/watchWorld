package com.rongyi.diamond.networklibrary.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.rongyi.diamond.baselibiary.utils.Utils;
import com.rongyi.diamond.networklibrary.mvp.PhotoContract;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 下午5:35
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class DownImageModel implements PhotoContract.Model {

    private static final String IMAGE_SAVE_PATH = "/Gank/";

    @Override
    public void saveImage(Subscriber<String> subscriber, final Context context, final String url) {
        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Bitmap bitmap = Picasso.with(context).load(url).get();
                    String path = saveBitmapToSDCard(bitmap, url, context);
                    subscriber.onNext(path);
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private String saveBitmapToSDCard(Bitmap bitmap, String url, Context context) {
        String SavePath = Utils.getSDCardPath() + IMAGE_SAVE_PATH;
        // save Bitmap
        try {
            File path = new File(SavePath);
            if (!path.exists())
                path.mkdirs();

            String[] split = url.split("\\/");
            String filepath = SavePath + split[split.length - 1];
            File file = new File(filepath);
            if (!file.exists())
                file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return filepath;
        } catch (Exception e) {
            String text = e.getMessage();
            e.printStackTrace();
        }
        return null;
    }

}
