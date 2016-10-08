package com.rongyi.diamond.baselibiary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongyi.diamond.baselibiary.R;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午7:02
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BasePullRefreshFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  super.onCreateView(inflater, container, savedInstanceState);
        setUpSwipeRefreshLayout(rootView, inflater, container);
        return rootView;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_base_pull_refresh;
    }

    private void setUpSwipeRefreshLayout(View rootView, LayoutInflater inflater, ViewGroup container) {
        if (rootView != null) {
            mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
            mSwipeRefreshLayout.addView(getFragmentContentView(inflater, container));
            mSwipeRefreshLayout.setOnRefreshListener(this);
            mSwipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_light,
                    R.color.holo_green_light, R.color.holo_orange_light,
                    R.color.holo_red_light);
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewComponent(view);
    }

    protected abstract void setUpViewComponent(View rootView);

    protected abstract View getFragmentContentView(LayoutInflater inflater, ViewGroup container);

}
