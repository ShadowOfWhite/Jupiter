package com.example.asus.startproject;

import android.app.Application;

import com.example.latte.ec.icon.FontEcModule;
import com.example.latte_core.app.Latte;
import com.example.latte_core.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by 杨淋 on 2018/4/25.
 * Describe：
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new DebugInterceptor("baidu",R.raw.text))
                .withIcon(new FontEcModule())
                .configure();
    }
}
