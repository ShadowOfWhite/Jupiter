package com.example.latte_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.delegates.web.WebDelegate;

/**
 * 作者：贪欢
 * 时间：2019/6/27
 * 描述：
 */
public abstract class Event implements IEvent {

    private Context context = null;
    private String action = null;
    private WebDelegate delegate = null;
    private String url = null;
    private WebView webView = null;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public WebView getWebView(){
        return delegate.getWebView();
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public WebDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(WebDelegate delegate) {
        this.delegate = delegate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
