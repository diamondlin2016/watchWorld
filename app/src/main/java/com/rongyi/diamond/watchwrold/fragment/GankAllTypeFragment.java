package com.rongyi.diamond.watchwrold.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.rongyi.diamond.baselibiary.base.BasePresenterFragment;
import com.rongyi.diamond.baselibiary.widget.RecyclerViewItemDivider;
import com.rongyi.diamond.networklibrary.bean.GankBean;
import com.rongyi.diamond.networklibrary.mvp.GankDataContract;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.adapter.GankDataAdapter;
import com.rongyi.diamond.watchwrold.app.AppParamContact;
import com.rongyi.diamond.watchwrold.presenter.GankDataPresenter;

import java.util.ArrayList;

import butterknife.Bind;

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

    @Bind(R.id.recycle_view)
    SuperRecyclerView mRecycleView;
    GankDataAdapter mNewTopsAdapter;
    private String mType;

    public static GankAllTypeFragment newInstance(@GankDataPresenter.GankDataType String type) {
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
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.widget_recycler_view;
    }

    @Override
    protected void getFragmentArguments() {
        mType = getArguments().getString(AppParamContact.PARAM_KEY_TYPE, GankDataPresenter.TYPE_ALL);
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

        mRecycleView.addItemDecoration(new RecyclerViewItemDivider(getActivity(), RecyclerViewItemDivider.VERTICAL_LIST));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return 0;
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        itemTouchHelper.attachToRecyclerView(mRecycleView.getRecyclerView());
        mPresenter.getGankData();
    }

    @Override
    public void onDataRefresh(ArrayList<GankBean> list) {
        if (mNewTopsAdapter == null) {
            mNewTopsAdapter = new GankDataAdapter(getActivity());
            mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecycleView.setAdapter(mNewTopsAdapter);
        }
        mNewTopsAdapter.clearItems();
        mNewTopsAdapter.addItems(list);
    }

    @Override
    public void onDataMore(ArrayList<GankBean> list) {
        mNewTopsAdapter.addItems(list);
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
