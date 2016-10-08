package com.rongyi.diamond.baselibiary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午7:56
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseAbstractAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final String TAG = getClass().getSimpleName();
    protected final Context mContext;
    protected final LayoutInflater mLayoutInflater;

    protected List<T> mDataList = new ArrayList<>();

    public BaseAbstractAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public Context getContext() {
        return mContext;
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public T getItemData(int position) {
        return (position >= 0 && position < mDataList.size()) ? mDataList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 移除某一条记录
     *
     * @param position 移除数据的position
     */
    public void removeItem(int position) {
        if (position >= 0 && position < mDataList.size()) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 添加一条记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItem(T data, int position) {
        if (position >= 0 && position <= mDataList.size()) {
            mDataList.add(position, data);
            notifyItemInserted(position);
        }
    }

    /**
     * 添加一条记录
     *
     * @param data 需要加入的数据结构
     */
    public void addItem(T data) {
        addItem(data, mDataList.size());
    }

    /**
     * 移除所有记录
     */
    public void clearItems() {
        int size = mDataList.size();
        if (size > 0) {
            mDataList.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    /**
     * 批量添加记录
     *
     * @param data     需要加入的数据结构
     * @param position 插入位置
     */
    public void addItems(List<T> data, int position) {
        if (position >= 0 && position <= mDataList.size() && data != null && data.size() > 0) {
            mDataList.addAll(position, data);
            notifyItemRangeChanged(position, data.size());
        }
    }

    /**
     * 批量添加记录
     *
     * @param data 需要加入的数据结构
     */
    public void addItems(List<T> data) {
        addItems(data, mDataList.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder) {
            ((BaseViewHolder) holder).bindViewData(getItemData(position));
        }
    }
}
