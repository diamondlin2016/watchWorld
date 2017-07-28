package com.rongyi.diamond.baselibiary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongyi.diamond.baselibiary.widget.LoadingView;

import butterknife.ButterKnife;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/29 下午10:15
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    private LoadingView mLoginView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, rootView);
        mLoginView = new LoadingView(getActivity());
        return rootView;
    }

    protected abstract int getLayoutResource();

    protected void getFragmentArguments() {

    }

    public void showLoadingView() {
        mLoginView.show();
    }

    public void hideLoadingView() {
        mLoginView.hide();
    }

}

