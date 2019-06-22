package com.example.latte_core.app;

/**
 * Created by 杨淋 on 2018/4/24.
 * Describe：枚举类，在应用程序里面是唯一的单例，并且只能被初始化一次，
 * 非常安全的懒汉模式
 */

public enum  ConfigType {
    API_HOST,//存储网络请求的域名
    APPLICATION_CONTEXT,//全局上下文
    CONFIG_READY,//控制配置或初始化是否完成
    ICON,//存储自己的一些初始化项目
    INTERCEPTOR,
    HANDLER
}
