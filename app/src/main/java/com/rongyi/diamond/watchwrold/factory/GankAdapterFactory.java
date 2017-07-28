package com.rongyi.diamond.watchwrold.factory;

import android.content.Context;

import com.rongyi.diamond.watchwrold.adapter.BaseGankAdapter;
import com.rongyi.diamond.watchwrold.adapter.GankDataAdapter;
import com.rongyi.diamond.watchwrold.fragment.GankAllTypeFragment;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/7/28 上午10:10
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class GankAdapterFactory {

    public static BaseGankAdapter createAdapter(@GankAllTypeFragment.GankDataType String type, Context ctx) {
        BaseGankAdapter adapter;
        switch (type){
            case GankAllTypeFragment.TYPE_ALL:
            case GankAllTypeFragment.TYPE_ANDROID:
            case GankAllTypeFragment.TYPE_VIDEO:
            case GankAllTypeFragment.TYPE_EXTEND:
            case GankAllTypeFragment.TYPE_H5:
            case GankAllTypeFragment.TYPE_RECOMMEND:
            case GankAllTypeFragment.TYPE_APP:
            default:
                adapter = new GankDataAdapter(ctx);
        }
        return adapter;
    }
}
