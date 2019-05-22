package com.example.latte_core.net.interceptors;

import android.util.Log;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：贪欢
 * 时间：2019/5/14
 * 描述：
 */
public abstract class BaseInterceptor implements Interceptor {

    private static final String TAG = "BaseInterceptor";

    /**
     * 取出所有的参数，LinkedHashMap有序
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){

        final HttpUrl url = chain.request().url();
        Log.i(TAG, "getUrlParameters:请求信息： "+chain.request().url().query() );//获得完整的url信息
        int size = url.querySize();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for (int i = 0; i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }

        return params;
    }


    protected String getUrlParameters(Chain chain,String key){

        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    //这里为什么value是String？不应该是object吗
    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i),formBody.value(i));
        }
        return params;
    }

    protected String getBodyParameters(Chain chain,String key){

        return getBodyParameters(chain).get(key);
    }
}
