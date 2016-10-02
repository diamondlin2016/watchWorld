package com.rongyi.diamond.watchwrold.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rongyi.diamond.watchwrold.app.AppContact;
import com.rongyi.diamond.baselibiary.app.AppSpContact;
import com.rongyi.diamond.baselibiary.base.BaseActivity;
import com.rongyi.diamond.baselibiary.utils.SharedPreferencesHelper;
import com.rongyi.diamond.baselibiary.utils.ToastHelper;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.utils.ImageDisplayHelper;

import butterknife.Bind;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 下午5:26
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.rl)
    RelativeLayout mRl;
    @Bind(R.id.tv_hello)
    TextView mTvHello;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        setNavigationView();
    }


    private void setNavigationView() {
        View headerView = mNavView.getHeaderView(0);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tv_title);
        ImageView ivNav = (ImageView) headerView.findViewById(R.id.iv_nav);
        ImageDisplayHelper.displayLocalImage(AppContact.NAVIGATION_IMAGE_PATH,R.mipmap.bg,ivNav);
        tvTitle.setText(mSharedPreferencesHelper.getString(AppSpContact.NAVIGATION_TITLE, getString(R.string.default_description)));
        mNavView.setNavigationItemSelectedListener(this);
        initMenu();
    }

    private void initMenu() {
        //add Item Click
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id < 4) {
            ToastHelper.showToastMessage(String.valueOf(id));
        }
        switch (id) {
            case R.id.nav_night:
                boolean isNight = mSharedPreferencesHelper.getBoolean(AppSpContact.IS_NIGHT, false);
                ToastHelper.showToastMessage(!isNight ? "开启了夜间模式" : "关闭了夜间模式");
                isNight = !isNight;
                mSharedPreferencesHelper.putBoolean(AppSpContact.IS_NIGHT, isNight);
                changeTheme();
                break;
            case R.id.nav_setting:
                ToastHelper.showToastMessage("点击了设置按钮");
                break;
            case R.id.nav_change:
                ToastHelper.showToastMessage("点击了改变栏目按钮");
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //夜间模式相关
    private void initTheme() {
        if (SharedPreferencesHelper.getInstance().getBoolean(AppSpContact.IS_NIGHT, false)) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
    }

    private void changeTheme() {
        showAnimation();
        toggleThemeSetting();
        refreshUI();
    }

    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);
        mFab.setBackgroundResource(background.resourceId);
        mNavView.setBackgroundResource(background.resourceId);
        mRl.setBackgroundResource(background.resourceId);
        Resources resources = getResources();
        mTvHello.setTextColor(resources.getColor(textColor.resourceId));
        refreshStatusBar();
    }

    /**
     * 刷新 StatusBar
     */
    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }


    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    /**
     * 获取一个 View 的缓存视图
     *
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    /**
     * 切换主题设置
     */
    private void toggleThemeSetting() {
        if (mSharedPreferencesHelper.getBoolean(AppSpContact.IS_NIGHT, false)) {
            setTheme(R.style.NightTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }

}
