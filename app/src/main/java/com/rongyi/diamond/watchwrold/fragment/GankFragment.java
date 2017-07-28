package com.rongyi.diamond.watchwrold.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.rongyi.diamond.baselibiary.adapter.BaseViewPagerAdapter;
import com.rongyi.diamond.baselibiary.base.BaseViewPagerFragment;
import com.rongyi.diamond.watchwrold.R;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 上午10:29
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class GankFragment extends BaseViewPagerFragment {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindArray(R.array.gank_item_type)
    String[] mTitles;

    private ArrayList<BaseViewPagerFragment> mFragments;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_gank;
    }


    @Override
    protected void setUpViewComponent() {
        createFragments();
        mViewPager.setOffscreenPageLimit(mFragments.size() - 1); //ViewPager的缓存页面数量
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getFragmentManager(), mTitles, mFragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void createFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_ALL));
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_ANDROID));
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_VIDEO));
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_MEIZI));
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_EXTEND));
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_H5));
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_RECOMMEND));
        mFragments.add(GankAllTypeFragment.newInstance(GankAllTypeFragment.TYPE_APP));
    }

    @Override
    protected void onLazyLoad() {}


}
