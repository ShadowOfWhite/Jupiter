package com.example.latte_core.net.interceptors;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.example.latte_core.util.storage.LattePreference;

import java.io.IOException;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：贪欢
 * 时间：2019/6/28
 * 描述：
 */
public class AddCookieInterceptor implements Interceptor {
    @SuppressLint("CheckResult")
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();

        //noinspection ResultOfMethodCallIgnored
        Observable
                .just(LattePreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        //给原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie",cookie);
                    }
                });



        return chain.proceed(builder.build());
    }
}
