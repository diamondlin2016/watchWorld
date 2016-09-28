package com.rongyi.diamond.watchwrold;

import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongyi.diamond.baselibrary.app.AppSpContact;
import com.rongyi.diamond.baselibrary.base.BaseActivity;
import com.rongyi.diamond.baselibrary.utils.ToastHelper;

import java.io.File;

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

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        setSupportActionBar(mToolbar);
        setNavigationView();
        initTheme();
    }

    private void setNavigationView() {
        View headerView = mNavView.getHeaderView(0);
        TextView tvTitle = (TextView) headerView.findViewById(R.id.tv_title);
        LinearLayout llImage = (LinearLayout) headerView.findViewById(R.id.slid_bg);
        if (new File(getFilesDir().getPath() + "/bg.jpg").exists()) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), getFilesDir().getPath() + "/bg.jpg");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                llImage.setBackground(bitmapDrawable);
            }
            tvTitle.setText(mSharedPreferencesHelper.getString(AppSpContact.NAVIGATION_TITLE, getString(R.string.text_navigation_title)));
        }
        mNavView.setNavigationItemSelectedListener(this);
        initMenu();
    }

    private void initMenu() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id < 4) {
            ToastHelper.showToastMessage(String.valueOf(id));
        }
        switch (id) {
            case R.id.nav_night:
                Log.e("_______",(mSharedPreferencesHelper == null)+"");
                boolean isNight = mSharedPreferencesHelper.getBoolean(AppSpContact.IS_NIGHT, false);
                ToastHelper.showToastMessage(!isNight?"开启了夜间模式":"关闭了夜间模式");
                isNight = !isNight;
                mSharedPreferencesHelper.putBoolean(AppSpContact.IS_NIGHT,isNight);
//                finish();
//                startActivity(new Intent(this, MainActivity.class));
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
}
