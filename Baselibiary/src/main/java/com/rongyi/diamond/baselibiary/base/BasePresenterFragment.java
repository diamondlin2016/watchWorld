package com.rongyi.diamond.baselibiary.base;

import android.os.Bundle;

import com.rongyi.diamond.baselibiary.base.mvp.BasePresenter;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/2 下午2:11
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/2      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BasePresenterFragment<T extends BasePresenter> extends BaseViewPagerFragment {
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    public abstract T createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }


}
