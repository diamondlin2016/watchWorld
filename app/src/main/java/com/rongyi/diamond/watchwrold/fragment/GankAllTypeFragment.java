package com.rongyi.diamond.watchwrold.fragment;

import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.widget.SwipeRefreshLayout;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.rongyi.diamond.baselibiary.base.BasePresenterFragment;
import com.rongyi.diamond.networklibrary.bean.GankBean;
import com.rongyi.diamond.networklibrary.mvp.GankDataContract;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.adapter.BaseGankAdapter;
import com.rongyi.diamond.watchwrold.app.AppParamContact;
import com.rongyi.diamond.watchwrold.factory.GankAdapterFactory;
import com.rongyi.diamond.watchwrold.presenter.GankDataPresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午6:59
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class GankAllTypeFragment extends BasePresenterFragment<GankDataPresenter> implements GankDataContract.View {

    @BindView(R.id.recycle_view)
    SuperRecyclerView mRecycleView;
    BaseGankAdapter mGankDataAdapter;
    private String mType;

    //all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    public static final String TYPE_ALL = "all";//all
    public static final String TYPE_ANDROID = "Android";//
    public static final String TYPE_VIDEO = "休息视频";//
    public static final String TYPE_MEIZI = "福利";//
    public static final String TYPE_EXTEND = "拓展资源";//
    public static final String TYPE_H5 = "前端";//
    public static final String TYPE_RECOMMEND = "瞎推荐";//
    public static final String TYPE_APP = "App";//

    @StringDef({TYPE_ALL, TYPE_ANDROID, TYPE_VIDEO, TYPE_MEIZI, TYPE_EXTEND, TYPE_H5, TYPE_RECOMMEND, TYPE_APP})
    public @interface GankDataType {
    }

    public static GankAllTypeFragment newInstance(@GankAllTypeFragment.GankDataType String type) {
        Bundle bd = new Bundle();
        bd.putString(AppParamContact.PARAM_KEY_TYPE, type);
        GankAllTypeFragment fragment = new GankAllTypeFragment();
        fragment.setArguments(bd);
        return fragment;
    }


    @Override
    public GankDataPresenter createPresenter() {
        return new GankDataPresenter(this);
    }


    @Override
    public void onLazyLoad() {
        mPresenter.getGankData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.widget_recycler_view;
    }

    @Override
    protected void getFragmentArguments() {
        mType = getArguments().getString(AppParamContact.PARAM_KEY_TYPE, GankAllTypeFragment.TYPE_ALL);
    }

    @Override
    protected void setUpViewComponent() {
        mRecycleView.setRefreshingColorResources(R.color.holo_blue_light,
                R.color.holo_green_light, R.color.holo_orange_light,
                R.color.holo_red_light);
        mRecycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onLazyLoad();
            }
        });
        mRecycleView.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
                mPresenter.getMore();
            }
        }, 1);
    }

    @Override
    public void onDataRefresh(ArrayList<GankBean> list) {
        if (mGankDataAdapter == null) {
            mGankDataAdapter = GankAdapterFactory.createAdapter(mType, getContext());
            mRecycleView.setAdapter(mGankDataAdapter);
        }
        mGankDataAdapter.clearItems();
        mGankDataAdapter.addItems(list);
    }

    @Override
    public void onDataMore(ArrayList<GankBean> list) {
        mGankDataAdapter.addItems(list);
    }

    @Override
    public void onLoadError() {
        if (mRecycleView != null) {
            mRecycleView.hideMoreProgress();
            mRecycleView.hideProgress();
        }
    }

    @Override
    public String getRequestType() {
        return mType;
    }


}
