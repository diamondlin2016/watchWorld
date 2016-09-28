package com.rongyi.diamond.baselibiary.utils;

import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rongyi.diamond.baselibiary.R;
import com.rongyi.diamond.baselibiary.app.BaseApplication;


/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 下午7:34
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class ToastHelper {

    public static void showToastMessage(String msg) {
        showToastMessage(msg, Toast.LENGTH_SHORT);
    }

    public static void showToastMessage(String msg, int length) {
        if (StringHelper.notEmpty(msg)) {
            // create instance
            Toast toast = new Toast(BaseApplication.getContext());
            // inflate custom view
            View view = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.toast_layout, null);
            ((TextView) view.findViewById(R.id.tv_message)).setText(msg);
            // set custom view
            toast.setView(view);
            // set duration
            toast.setDuration(length);
            // set position
            int margin = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen.toast_vertical_margin);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, margin);
            // show toast
            toast.show();
        }
    }

    public static void showToastMessage(@StringRes int resId) {
        showToastMessage(resId, Toast.LENGTH_SHORT);
    }

    public static void showToastMessage(@StringRes int resId, int length) {
        if (resId != 0) {
            // create instance
            Toast toast = new Toast(BaseApplication.getContext());
            // inflate custom view
            View view = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.toast_layout, null);
            ((TextView) view.findViewById(R.id.tv_message)).setText(resId);
            // set custom view
            toast.setView(view);
            // set duration
            toast.setDuration(length);
            // set position
            int margin = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen.toast_vertical_margin);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, margin);
            // show toast
            toast.show();
        }
    }
}
