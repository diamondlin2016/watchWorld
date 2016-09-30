package com.rongyi.diamond.networklibrary.bean;

import com.google.gson.annotations.SerializedName;
import com.rongyi.diamond.networklibrary.bean.base.BaseBean;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 下午2:40
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class ImageItem extends BaseBean {

    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String mImageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
