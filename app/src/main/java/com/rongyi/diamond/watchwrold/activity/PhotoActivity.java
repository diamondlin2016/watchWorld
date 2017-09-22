package com.rongyi.diamond.watchwrold.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.rongyi.diamond.baselibiary.base.BasePresenterActivity;
import com.rongyi.diamond.baselibiary.utils.StringHelper;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.app.AppParamContact;
import com.rongyi.diamond.watchwrold.presenter.PhotoPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 下午4:58
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class PhotoActivity extends BasePresenterActivity<PhotoPresenter> implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.photo_view)
    PhotoView mPhotoView;
    private String mUrl;
    private PhotoViewAttacher mAttacher;

    public static void startActivity(Activity context, View view, String url) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context, view, context.getString(R.string.animation_transition));
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(AppParamContact.PARAM_KEY_DATA, url);
        ActivityCompat.startActivity(context, intent,
                options.toBundle());
    }

    @Override
    public PhotoPresenter createPresenter() {
        return new PhotoPresenter();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo;
    }

    @Override
    protected void getArgumentsForActivityIntent(Intent intent) {
        mUrl = intent.getStringExtra(AppParamContact.PARAM_KEY_DATA);
        if (StringHelper.isEmpty(mUrl))
            finish();
    }

    @Override
    protected void setViewComponent() {
        ViewCompat.setTransitionName(mPhotoView, getString(R.string.animation_transition));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.menu_photo);
        mToolbar.setOnMenuItemClickListener(this);

        mAttacher = new PhotoViewAttacher(mPhotoView);
        mAttacher.setZoomable(true);
        Picasso.with(this)
                .load(mUrl)
                .into(mPhotoView);
        mAttacher.update();

    }

    @OnClick(R.id.photo_view)
    void finishActivity() {
        finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                mPresenter.saveImage(this, mUrl);
                break;
            case R.id.action_share:
                mPresenter.shareImage(this, "", "", "");
                break;
        }
        return false;
    }
}
