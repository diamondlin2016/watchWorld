package com.rongyi.diamond.networklibrary.api;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 上午12:29
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class HttpApiMethods {
    static volatile HttpApiMethods singleton = null;
    private AppApiService appApiService;
    //构造方法私有
    private HttpApiMethods(AppApiService appApiService) {
        this.appApiService = appApiService;
    }

    //在访问HttpMethods时创建单例
    public static HttpApiMethods with(AppApiService appApiService) {
        if (singleton == null) {
            synchronized (HttpApiMethods.class) {
                if (singleton == null) {
                    singleton = new Builder(appApiService).build();
                }
            }
        }
        return singleton;
    }

    public static class Builder {
        private AppApiService appApiService;

        public Builder(AppApiService appApiService) {
            if (appApiService == null) {
                throw new IllegalArgumentException("AppApiService must not be null.");
            }
            this.appApiService = appApiService;
        }

        public HttpApiMethods build() {
            return new HttpApiMethods(appApiService);
        }
    }



}
