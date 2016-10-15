package com.rongyi.diamond.baselibiary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午7:32
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseViewPagerFragment extends BaseFragment {
    protected boolean isPrepared = false;//页面是否已初始化
    protected boolean isVisible = false;//页面是否显示
    protected boolean isInitFinish = false;//数据是否已初始化

    public void setInitFinish(boolean isInitFinish) {
        this.isInitFinish = isInitFinish;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        onFragmentVisible();
        setUpViewComponent();
    }

    protected abstract void setUpViewComponent();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            this.isVisible = true;
            onFragmentVisible();
        } else {
            this.isVisible = false;
            onFragmentInvisible();
        }
    }

    protected void onFragmentInvisible() {

    }

    protected void onFragmentVisible() {
        if (!isPrepared || !isVisible) {
            return;
        }
        if (!isInitFinish) {
            isInitFinish = true;
            onLazyLoad();
        }
    }

    protected abstract void onLazyLoad();

}
