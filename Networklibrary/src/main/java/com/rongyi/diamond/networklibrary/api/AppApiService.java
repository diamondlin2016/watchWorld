package com.rongyi.diamond.networklibrary.api;

import com.rongyi.diamond.networklibrary.bean.GankBean;
import com.rongyi.diamond.networklibrary.bean.ImageData;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午12:36
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public interface AppApiService {

    /**
     * 获取首页图片
     *
     * @param size 获取图片张数
     */
    @GET("http://lab.zuimeia.com/wallpaper/category/1/")
    Observable<HttpResultV1<ImageData>> getImage(@Query("page_size") int size);


    /*
    * @path type :all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    * @path pageSize :最大50
    * @path pageNo :页码
    * api_url:http://gank.io/api
    * */
    @GET("http://gank.io/api/search/query/listview/category/{type}/count/{pageSize}/page/{pageNo} ")
    Observable<HttpResultGank<ArrayList<GankBean>>> getGankData(@Path("type") String type,
                                                            @Path("pageSize") int pageSize,
                                                            @Path("pageNo") int pageNo);
}
