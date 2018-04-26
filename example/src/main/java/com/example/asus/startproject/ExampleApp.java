package com.example.asus.startproject;

import android.app.Application;

import com.example.latte_core.app.Latte;

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
                .configure();
    }
}
