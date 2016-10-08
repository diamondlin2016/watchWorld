package com.rongyi.diamond.watchwrold.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.rongyi.diamond.baselibiary.base.BaseViewPagerFragment;
import com.rongyi.diamond.baselibiary.widget.RecyclerViewItemDivider;
import com.rongyi.diamond.networklibrary.bean.NewsBean;
import com.rongyi.diamond.networklibrary.mvp.NewTopsContract;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.adapter.NewTopsAdapter;
import com.rongyi.diamond.watchwrold.presenter.NewTopsPresenter;

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
public class NewTopsFragment extends BaseViewPagerFragment<NewTopsPresenter>implements NewTopsContract.View {

    @Bind(R.id.recycle_view)
    SuperRecyclerView mRecycleView;
    NewTopsAdapter mNewTopsAdapter;
    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.recycler_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setUpViewComponent() {
        mNewTopsAdapter = new NewTopsAdapter(getActivity());
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.addItemDecoration(new RecyclerViewItemDivider(getActivity(), RecyclerViewItemDivider.VERTICAL_LIST));
        mRecycleView.setAdapter(mNewTopsAdapter);
    }

    @Override
    public NewTopsPresenter createPresenter() {
        return new NewTopsPresenter(this);
    }

    @Override
    public void onDataRefresh(ArrayList<NewsBean> list) {
        mNewTopsAdapter.clearItems();
        mNewTopsAdapter.addItems(list);
    }

    @Override
    public void onDataMore(ArrayList<NewsBean> list) {
        mNewTopsAdapter.addItems(list);
    }
}
