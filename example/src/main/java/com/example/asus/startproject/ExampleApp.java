package com.example.asus.startproject;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.asus.startproject.event.ShareEvent;
import com.example.latte.ec.database.DatabaseManager;
import com.example.latte.ec.icon.FontEcModule;
import com.example.latte_core.app.Latte;
import com.example.asus.startproject.event.TestEvent;
import com.example.latte_core.net.interceptors.AddCookieInterceptor;
import com.example.latte_core.net.interceptors.DebugInterceptor;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_core.util.callback.IGlobalCallback;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;
import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Created by 杨淋 on 2018/4/25.
 * Describe：
 */

// TODO: 2019/6/27 这是老师是MultiDexApplication
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


                Fragmentation.builder()
                        // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                        .stackViewMode(Fragmentation.BUBBLE)
                        .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                        /**
                         * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                         * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                         */
                        .handleException(new ExceptionHandler() {
                            @Override
                            public void onException(Exception e) {
                                // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                                // Bugtags.sendException(e);
                            }
                        })
                        .install();


        Latte.init(this)
                .withNativeApiHost("http://mock.fulingjie.com/mock-android/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("debug",R.raw.text))
                .withJavascriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                .withWebEvent("share",new ShareEvent())
                //添加Cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                .withWebHost("https://www.baidu.com/")
                .configure();

        Logger.addLogAdapter(new AndroidLogAdapter());
        DatabaseManager.getInstance().init(this);
        initStetho();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@NonNull Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplication())){
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplication());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@NonNull Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplication())){
                            JPushInterface.stopPush(Latte.getApplication());
                        }
                    }
                });



    }

    private void initStetho(){
        Stetho.initializeWithDefaults(this);

    }
}
