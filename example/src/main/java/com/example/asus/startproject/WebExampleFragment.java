package com.example.asus.startproject;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte_core.fragments.web.IPageLoadListener;
import com.example.latte_core.fragments.web.IUrlHandler;
import com.example.latte_core.fragments.web.IWebViewInitializer;
import com.example.latte_core.fragments.web.WebFragment;
import com.example.latte_core.fragments.web.WebViewInitializer;
import com.example.latte_core.fragments.web.chromeclient.WebChromeClientImpl;
import com.example.latte_core.fragments.web.route.RouteKeys;

/**
 * 作者：贪欢
 * 时间：2019/8/17
 * 描述：
 */
public class WebExampleFragment extends WebFragment implements IPageLoadListener {


    //简单工厂，让参数更加透明
    public static WebExampleFragment create(String url){

        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebExampleFragment fragment = new WebExampleFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public IUrlHandler setUrlHandler() {
        return new UrlHandlerExample();
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
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().creatWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebVIewClientExample client = new WebVIewClientExample(this);
        client.setPageLoadListener(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    @Override
    public void onLoadStart() {

    }

    @Override
    public void onLoadEnd() {

    }
}
