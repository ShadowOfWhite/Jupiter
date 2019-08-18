package com.example.latte_core.fragments.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.example.latte_core.fragments.web.event.Event;
import com.example.latte_core.fragments.web.event.EventManager;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public final class LatteWebInterface {

    private final WebFragment DELEGATE;

    private LatteWebInterface(WebFragment delegate) {
        this.DELEGATE = delegate;
    }

    public static LatteWebInterface create(WebFragment delegate){
        return new LatteWebInterface(delegate);
    }

    @JavascriptInterface //Android4.4以后必须要加
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
