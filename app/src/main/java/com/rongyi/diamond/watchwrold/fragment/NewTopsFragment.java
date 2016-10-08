package com.rongyi.diamond.watchwrold.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.rongyi.diamond.baselibiary.base.BaseViewPagerFragment;
import com.rongyi.diamond.baselibiary.base.mvp.BasePresenter;
import com.rongyi.diamond.baselibiary.widget.RecyclerViewItemDivider;
import com.rongyi.diamond.watchwrold.R;

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
public class NewTopsFragment<NewTopsPresenter> extends BaseViewPagerFragment {

    @Bind(R.id.recycle_view)
    SuperRecyclerView mRecycleView;

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
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.addItemDecoration(new RecyclerViewItemDivider(getActivity(), RecyclerViewItemDivider.VERTICAL_LIST));
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}
