package com.example.latte_core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    //WebViewClient针对浏览器本身行为的一个控制
    WebViewClient initWebViewClient();

    //WebChromeClient针对页面的一个控制
    WebChromeClient initWebChromeClient();
}
