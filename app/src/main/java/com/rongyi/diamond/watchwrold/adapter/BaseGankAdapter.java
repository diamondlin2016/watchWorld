package com.rongyi.diamond.watchwrold.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.rongyi.diamond.baselibiary.adapter.BaseAbstractAdapter;
import com.rongyi.diamond.networklibrary.bean.GankBean;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 上午10:11
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public abstract class BaseGankAdapter extends BaseAbstractAdapter<GankBean> {
    public BaseGankAdapter(Context context) {
        super(context);
    }

    abstract RecyclerView.LayoutManager createLayoutManger();

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(createLayoutManger());
    }
}
