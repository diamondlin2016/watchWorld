package com.rongyi.diamond.watchwrold.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rongyi.diamond.baselibiary.adapter.BaseViewHolder;
import com.rongyi.diamond.networklibrary.bean.GankBean;
import com.rongyi.diamond.watchwrold.R;
import com.rongyi.diamond.watchwrold.activity.PhotoActivity;
import com.rongyi.diamond.watchwrold.utils.ImageDisplayHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 下午4:32
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class GankMeiziAdapter extends BaseGankAdapter {

    public GankMeiziAdapter(Context context) {
        super(context);
    }

    @Override
    RecyclerView.LayoutManager createLayoutManger() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankMeiziHolder(mLayoutInflater.inflate(R.layout.item_gank_meizi, parent, false), this);
    }

    public static class GankMeiziHolder extends BaseViewHolder<GankBean> {
        @BindView(R.id.iv_img)
        ImageView mIvImg;
        @BindView(R.id.tv_desc)
        TextView mTvDesc;

        GankMeiziAdapter mAdapter;
        private GankBean mData;

        GankMeiziHolder(View view, GankMeiziAdapter adapter) {
            super(view);
            mAdapter = adapter;
        }

        @Override
        public void bindViewData(GankBean data) {
            mData = data;
            mTvDesc.setText(data.desc);
            ImageDisplayHelper.displayImage(data.url, 0, mIvImg);
        }

        @OnClick(R.id.iv_img)
        void showBigImage() {
            PhotoActivity.startActivity((Activity) mAdapter.mContext, mIvImg, mData.url);
        }
    }
}
