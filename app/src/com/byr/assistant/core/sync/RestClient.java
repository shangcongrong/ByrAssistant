package com.byr.assistant.core.sync;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * User: orange
 * Date: 13-11-25
 * Time: 下午9:15
 */
public class RestClient {

    private static final String BASE_URL = "http://byrcampusassistant.duapp.com/";

    private static FinalHttp client = new FinalHttp();


    public static void get(String url, AjaxParams params, AjaxCallBack<? extends java.lang.Object> callBack) {
        client.get(getAbsoluteUrl(url), params, callBack);
    }

//    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        client.post(getAbsoluteUrl(url), params, responseHandler);
//    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
