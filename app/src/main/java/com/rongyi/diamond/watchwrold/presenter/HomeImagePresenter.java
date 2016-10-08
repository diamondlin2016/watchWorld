package com.rongyi.diamond.watchwrold.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;

import com.rongyi.diamond.watchwrold.app.AppContact;
import com.rongyi.diamond.baselibiary.app.AppSpContact;
import com.rongyi.diamond.baselibiary.app.BaseApplication;
import com.rongyi.diamond.baselibiary.base.mvp.BasePresenter;
import com.rongyi.diamond.baselibiary.utils.SharedPreferencesHelper;
import com.rongyi.diamond.baselibiary.utils.ToastHelper;
import com.rongyi.diamond.networklibrary.R;
import com.rongyi.diamond.networklibrary.app.NetworkApplication;
import com.rongyi.diamond.networklibrary.bean.ImageData;
import com.rongyi.diamond.networklibrary.model.HomeImageModel;
import com.rongyi.diamond.networklibrary.mvp.HomeImageContract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:42
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class HomeImagePresenter extends BasePresenter implements HomeImageContract.Presenter {
    private final HomeImageContract.View mView;
    HomeImageModel mHomeImageModel;

    public HomeImagePresenter(HomeImageContract.View view) {
        mView = view;
    }

    @Override
    public void getImage(int size) {
        if (mHomeImageModel == null) {
            mHomeImageModel = new HomeImageModel(NetworkApplication.getContext().getHttpApiMethods());
        }
        mSubscriptions.clear();
        Subscriber<ImageData> subscriber = new Subscriber<ImageData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastHelper.showToastMessage(e.getMessage());
            }

            @Override
            public void onNext(ImageData imageData) {
                saveImage(imageData);
            }
        };
        mHomeImageModel.getImage(subscriber, mView.getImageSize());
        mSubscriptions.add(subscriber);
    }


    public void saveImage(ImageData imageData) {
        Observable.just(imageData).
                subscribeOn(AndroidSchedulers.mainThread()).
                observeOn(Schedulers.io()).
                subscribe(new Action1<ImageData>() {
                    @Override
                    public void call(ImageData imageData) {
                        if (imageData.getImages() != null) {
                            {
                                try {
                                    Bitmap bitmap = BitmapFactory.decodeStream(
                                            new URL(AppContact.IMAGE_URL_HEAD.concat(imageData.getImages().get(0).getImageUrl()).concat("?imageMogr/v2/auto-orient/thumbnail/480x320/quality/100")).
                                                    openConnection().getInputStream());
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(AppContact.NAVIGATION_IMAGE_PATH)));
                                    Palette palette = Palette.from(bitmap).generate();
                                    int color = 0x000000;
                                    int vibrant = palette.getVibrantColor(color);
                                    int vibrantDark = palette.getDarkVibrantColor(color);
                                    if (vibrant == 0)
                                        vibrant = vibrantDark;
                                    if (vibrant == 0)
                                        vibrant = getRandomPrimaryColor();
                                    int muted = palette.getMutedColor(color);
                                    int mutedDark = palette.getDarkMutedColor(color);
                                    if (muted == 0)
                                        muted = mutedDark;
                                    if (muted == 0)
                                        muted = ContextCompat.getColor(BaseApplication.getContext(), R.color.colorAccent);
                                    DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.DATE_FIELD);
                                    SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.getInstance();
                                    sharedPreferencesHelper.putString(AppSpContact.NAVIGATION_TITLE, imageData.getImages().get(0).getDescription());
                                    sharedPreferencesHelper.putInt(AppSpContact.VIBRANT, vibrant);
                                    sharedPreferencesHelper.putInt(AppSpContact.MUTED, muted);
                                    sharedPreferencesHelper.putString(AppSpContact.IMAGE_GET_TIME, dateFormat.format(new Date()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                });
    }

    private int getRandomPrimaryColor() {
        int[] primaryInt = new int[]{
                R.color.colorBlueGreyPrimary,
                R.color.colorBluePrimary,
                R.color.colorBrownPrimary,
                R.color.colorCyanPrimary,
                R.color.colorDeepOrangePrimary,
                R.color.colorDeepPurplePrimary,
                R.color.colorGreenPrimary,
                R.color.colorIndigoPrimary,
                R.color.colorLightGreenPrimary,
                R.color.colorLimePrimary,
                R.color.colorRedPrimary,
                R.color.colorPinkPrimary,
                R.color.colorPrimary
        };
        return ContextCompat.getColor(BaseApplication.getContext(), primaryInt[new Random().nextInt(14)]);
    }

}
