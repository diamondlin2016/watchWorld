package com.rongyi.diamond.watchwrold.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rongyi.diamond.baselibiary.adapter.BaseViewHolder;
import com.rongyi.diamond.networklibrary.bean.GankBean;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.utils.ImageDisplayHelper;

import butterknife.BindView;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午7:58
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class GankDataAdapter extends BaseGankAdapter {

    public GankDataAdapter(Context context) {
        super(context);
    }

    @Override
    RecyclerView.LayoutManager createLayoutManger() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankDataHolder(mLayoutInflater.inflate(R.layout.item_new_tops, parent, false), this);
    }

    public static class GankDataHolder extends BaseViewHolder<GankBean> {
        @BindView(R.id.item_image_id)
        ImageView mItemImageId;
        @BindView(R.id.item_text_id)
        TextView mItemTextId;
        @BindView(R.id.item_text_source_id)
        TextView mItemTextSourceId;

        GankDataAdapter mAdapter;

        GankDataHolder(View view, GankDataAdapter adapter) {
            super(view);
            mAdapter = adapter;
        }

        @Override
        public void bindViewData(GankBean data) {
            mItemTextId.setText(data.desc);
            mItemTextSourceId.setText(data.publishedAt);
            ImageDisplayHelper.displayImage(data.url, R.drawable.ic_menu_slideshow, mItemImageId);
        }
    }
}
