package com.rongyi.diamond.networklibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/10/8 下午11:50
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/10/8      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class NewsBean implements Parcelable {
    /**
     * docid
     */
    @SerializedName("docid")
    private String docid;
    /**
     * 标题
     */
    @SerializedName("title")
    private String title;
    /**
     * 小内容
     */
    @SerializedName("digest")
    private String digest;
    /**
     * 图片地址
     */
    @SerializedName("imgsrc")
    private String imgsrc;
    /**
     * 来源
     */
    @SerializedName("source")
    private String source;
    /**
     * 时间
     */
    @SerializedName("ptime")
    private String ptime;
    /**
     * TAG
     */
    @SerializedName("tag")
    private String tag;

    public boolean hasFadedIn=false;

    protected NewsBean(Parcel in) {
        docid = in.readString();
        title = in.readString();
        digest = in.readString();
        imgsrc = in.readString();
        source = in.readString();
        ptime = in.readString();
        tag = in.readString();
        hasFadedIn = in.readByte() != 0;
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel in) {
            return new NewsBean(in);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(docid);
        dest.writeString(title);
        dest.writeString(digest);
        dest.writeString(imgsrc);
        dest.writeString(source);
        dest.writeString(ptime);
        dest.writeString(tag);
        dest.writeByte((byte) (hasFadedIn ? 1 : 0));
    }
}
