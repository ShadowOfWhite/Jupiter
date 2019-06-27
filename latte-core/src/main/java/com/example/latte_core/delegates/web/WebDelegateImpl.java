package com.example.latte_core.delegates.web;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte_core.app.Latte;
import com.example.latte_core.delegates.web.chromeclient.WebChromeClientImpl;
import com.example.latte_core.delegates.web.client.WebViewClientImpl;
import com.example.latte_core.delegates.web.route.RouteKeys;
import com.example.latte_core.delegates.web.route.Router;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener iPageLoadListener = null;

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    public void setPageLoadListener(IPageLoadListener listener){
        this.iPageLoadListener = listener;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        if (getUrl() != null){
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().creatWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl client = new WebViewClientImpl(this);
        if (iPageLoadListener != null){
            client.setPageLoadListener(iPageLoadListener);
        }
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
