package com.rongyi.diamond.baselibiary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rongyi.diamond.baselibiary.base.mvp.BaseView;
import com.rongyi.diamond.baselibiary.base.mvp.LogicProxy;
import com.rongyi.diamond.baselibiary.utils.SharedPreferencesHelper;
import com.rongyi.diamond.baselibiary.widget.LoadingView;

import butterknife.ButterKnife;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/28 上午12:32
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseActivity extends AppCompatActivity {
    public LoadingView mLoadingView;
    public SharedPreferencesHelper mSharedPreferencesHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        mSharedPreferencesHelper = SharedPreferencesHelper.getInstance();
        mLoadingView = new LoadingView(this);
        onInitView();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    public void showLoadView() {
        mLoadingView.show();
    }

    public void hideLoadView() {
        mLoadingView.hide();
    }

    public <T> T getLogicImpl(Class cls, BaseView o) {
        return LogicProxy.getInstance().bind(cls, o);
    }

    @Override
    public void onBackPressed() {
        if (mLoadingView.isShowing()){
            mLoadingView.dismiss();
        }else {
            super.onBackPressed();
        }
    }


}
