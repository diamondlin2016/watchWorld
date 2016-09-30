package com.rongyi.diamond.networklibrary.bean;

import com.google.gson.annotations.SerializedName;
import com.rongyi.diamond.networklibrary.bean.base.BaseBean;

import java.util.ArrayList;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午11:14
 * Description:首页图片数据结构
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class ImageData extends BaseBean {

    @SerializedName("images")
    private ArrayList<ImageItem> images;

    public ArrayList<ImageItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageItem> images) {
        this.images = images;
    }



}
