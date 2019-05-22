package com.example.latte_core.net.interceptors;

import android.support.annotation.RawRes;
import android.util.Log;

import com.example.latte_core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者：贪欢
 * 时间：2019/5/14
 * 描述：
 */
public class DebugInterceptor extends BaseInterceptor {
    private static final String TAG = "DebugInterceptor";

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String url, int rawId) {
        this.DEBUG_URL = url;
        this.DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain,@RawRes int rawId){
        final String json = FileUtil.getRawFile(rawId);
        Log.i(TAG, "debugResponse: 返回内容为："+json);
        return getResponse(chain,json);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {

        final String url = chain.request().url().toString();
        Log.i(TAG, "intercept: 打印实验："+url);
        if (url.contains(DEBUG_URL)){
            Log.i(TAG, "intercept: 进入拦截器");
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
