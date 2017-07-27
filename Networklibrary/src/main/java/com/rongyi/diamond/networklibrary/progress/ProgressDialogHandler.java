package com.rongyi.diamond.networklibrary.progress;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.rongyi.diamond.baselibiary.utils.StringHelper;
import com.rongyi.diamond.networklibrary.R;


/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/11/18 下午2:33
 * Description: 加载进度ProgressDialog控制类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/11/18      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private AlertDialog pd;
    private String message;
    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener progressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        mProgressCancelListener = progressCancelListener;
        this.cancelable = cancelable;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void initProgressDialog() {
        if (pd == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AlertDialog_Transparent);
            View inflate = View.inflate(context, R.layout.dialog_progress, null);
            TextView tv_message = (TextView) inflate.findViewById(R.id.tv_message);
            builder.setView(inflate);
            builder.setCancelable(cancelable);
            pd = builder.create();

            if (StringHelper.notEmpty(message)) {
                tv_message.setText(message);
            }
            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
