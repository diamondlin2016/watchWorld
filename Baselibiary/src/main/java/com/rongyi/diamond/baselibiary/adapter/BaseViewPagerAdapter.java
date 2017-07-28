package com.rongyi.diamond.baselibiary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rongyi.diamond.baselibiary.base.BaseViewPagerFragment;

import java.util.List;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 上午10:45
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private final List<BaseViewPagerFragment> mFragments;
    private String[] mTitles;

    public BaseViewPagerAdapter(FragmentManager fm, String[] titles, List<BaseViewPagerFragment> fragments) {
        super(fm);
        mTitles = titles;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles[position];
    }
}
