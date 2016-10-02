package com.rongyi.diamond.baselibiary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rongyi.diamond.baselibiary.base.mvp.BasePresenter;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/2 下午1:47
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/2      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BasePresenterActivity<T extends BasePresenter> extends BaseActivity {
    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    public abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }
}
