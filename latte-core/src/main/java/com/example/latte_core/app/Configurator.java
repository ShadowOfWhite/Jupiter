package com.example.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

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

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
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
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
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

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){

        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
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
