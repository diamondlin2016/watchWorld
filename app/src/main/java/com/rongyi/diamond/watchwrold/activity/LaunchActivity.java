package com.rongyi.diamond.watchwrold.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.rongyi.diamond.baselibiary.base.BasePresenterActivity;
import com.rongyi.diamond.networklibrary.mvp.HomeImageContract;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.presenter.HomeImagePresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/29 下午8:51
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class LaunchActivity extends BasePresenterActivity<HomeImagePresenter> implements Animation.AnimationListener,HomeImageContract.View {

    @Bind(R.id.tv_welcome)
    TextView mTvWelcome;
    ScaleAnimation scaleAnimation;
    private boolean mHasEnd = false;
    private boolean mHasClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewComponent();
    }

    protected void setViewComponent() {
        mPresenter.getImage(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        scaleAnimation = new ScaleAnimation(1f, 3f, 1f, 3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        mTvWelcome.setAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scaleAnimation.startNow();
            }
        }, 200);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_launch;
    }


    @Override
    public HomeImagePresenter createPresenter() {
        return new HomeImagePresenter(this);
    }

    @OnClick(R.id.fl_root)
    void enterHome() {
        if (mHasEnd) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        mHasClick = true;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mHasEnd = true;
        if (mHasClick){
            enterHome();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public int getImageSize() {
        return 1;
    }
}
