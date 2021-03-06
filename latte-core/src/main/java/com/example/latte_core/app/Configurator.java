package com.example.latte_core.app;

import android.app.Activity;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.example.latte_core.fragments.web.event.Event;
import com.example.latte_core.fragments.web.event.EventManager;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by 杨淋 on 2018/4/24.
 * Describe：这个类是进行一些配置文件的存储以及获取,单例
 */

public class Configurator {
//    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();//WeakHashMap比HashMap要好，听说是不用的键值对就会进行回收，优化了内存
    private static final HashMap<String,Object> LATTE_CONFIGS = new HashMap<>();//因为是配置文件，所以如果用WeakHashMap的话，配置的键值对可能会被回收，所以不能用WeakHashMap
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();


    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
        LATTE_CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);

    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    //配置完成后调用此方法
    public final void configure(){
        Utils.init(Latte.getApplication());
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withNativeApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.NATIVE_API_HOST.name(),host);
        return this;
    }

    public final Configurator withWebApiHost(String host){
        //只留下域名，否则无法同步cookie，不能带http://或末尾的/
        String hostName = host
                .replace("http://","")
                .replace("https://","");
        hostName = hostName.substring(0,hostName.lastIndexOf('/'));
        LATTE_CONFIGS.put(ConfigType.WEB_API_HOST.name(),hostName);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor icon){
        ICONS.add(icon);
        return this;
    }



    public final Configurator withInterceptor(Interceptor interceptor){

        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    //浏览器加载的host
    public Configurator withWebHost(String host){
        LATTE_CONFIGS.put(ConfigType.WEB_HOST.name(),host);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigType.ACTIVITY.name(), activity);
        return this;
    }


    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){

        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
        return this;

    }

    public final Configurator withJavascriptInterface(String name){
        LATTE_CONFIGS.put(ConfigType.JAVASCRIPT_INTERFACE.name(),name);
        return this;
    }

    public final Configurator withWebEvent(String action, Event event){
        EventManager.getInstance().addEvent(action,event);
        return this;
    }



    //初始化字体图标
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i =1; i<ICONS.size(); i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    //用于判断是否完成配置
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not read,call configure");
        }
    }

    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
