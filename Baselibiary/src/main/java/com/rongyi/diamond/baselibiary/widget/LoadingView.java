package com.rongyi.diamond.baselibiary.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.rongyi.diamond.baselibiary.R;


/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 上午1:01
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class LoadingView extends Dialog implements View.OnClickListener {

    int[] colors = new int[]{
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#2B3E51"),
            android.graphics.Color.parseColor("#00BD9C"),
            android.graphics.Color.parseColor("#227FBB"),
            android.graphics.Color.parseColor("#7F8C8D"),
            android.graphics.Color.parseColor("#FFCC5C"),
            android.graphics.Color.parseColor("#D55400"),
            android.graphics.Color.parseColor("#1AAF5D"),
    };

    public LoadingView(Context context) {
        super(context, R.style.loading_dialog_style);
        onInit();
    }

    private void onInit() {
        View view = getLayoutInflater().inflate(R.layout.widget_loading, null);

        ProgressBar mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        DoubleBounce doubleBounce = new DoubleBounce();
        doubleBounce.setBounds(0, 0,
                100,
                100);
        doubleBounce.setColor(colors[7]);
        mProgressBar.setIndeterminateDrawable(doubleBounce);

        view.setOnClickListener(this);
        setContentView(view);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }

    @Override
    public void show() {
        super.show();
    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

}
