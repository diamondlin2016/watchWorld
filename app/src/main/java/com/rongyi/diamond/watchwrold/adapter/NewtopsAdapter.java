package com.rongyi.diamond.watchwrold.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongyi.diamond.baselibiary.adapter.BaseAbstractAdapter;
import com.rongyi.diamond.baselibiary.adapter.BaseViewHolder;
import com.rongyi.diamond.networklibrary.bean.NewsBean;
import com.rongyi.diamond.watchwrold.R;

import butterknife.Bind;

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
public class NewTopsAdapter extends BaseAbstractAdapter<NewsBean> {

    public NewTopsAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewTopsHolder(mLayoutInflater.inflate(R.layout.item_new_tops, parent, false), this);
    }

    public static class NewTopsHolder extends BaseViewHolder<NewsBean> {
        @Bind(R.id.item_image_id)
        ImageView mItemImageId;
        @Bind(R.id.item_text_id)
        TextView mItemTextId;
        @Bind(R.id.item_text_source_id)
        TextView mItemTextSourceId;
        @Bind(R.id.zhihu_item_layout)
        LinearLayout mZhihuItemLayout;

        NewTopsAdapter mAdapter;

        public NewTopsHolder(View view, NewTopsAdapter adapter) {
            super(view);
            mAdapter = adapter;
        }

        @Override
        public void bindViewData(NewsBean data) {

        }
    }
}
