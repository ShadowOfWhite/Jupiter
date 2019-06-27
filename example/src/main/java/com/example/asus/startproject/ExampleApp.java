package com.example.asus.startproject;

import android.app.Application;

import com.example.latte.ec.database.DatabaseManager;
import com.example.latte.ec.icon.FontEcModule;
import com.example.latte_core.app.Latte;
import com.example.asus.startproject.event.TestEvent;
import com.example.latte_core.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by 杨淋 on 2018/4/25.
 * Describe：
 */

// TODO: 2019/6/27 这是老师是MultiDexApplication
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://mock.fulingjie.com/mock-android/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("debug",R.raw.text))
                .withJavascriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                .configure();

        Logger.addLogAdapter(new AndroidLogAdapter());
        DatabaseManager.getInstance().init(this);
        initStetho();

    }

    private void initStetho(){
        Stetho.initializeWithDefaults(this);

    }
}
