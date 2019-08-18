package com.example.latte_core.fragments.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.latte_core.app.ConfigType;
import com.example.latte_core.app.Latte;
import com.example.latte_core.fragments.web.IPageLoadListener;
import com.example.latte_core.fragments.web.WebFragment;
import com.example.latte_core.fragments.web.route.Router;
import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_core.util.log.LatteLogger;
import com.example.latte_core.util.storage.LattePreference;


/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebFragment DELEGATE;
    private IPageLoadListener iPageLoadListener = null;
    private static Handler HANDLER = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.iPageLoadListener = listener;
    }

    public WebViewClientImpl(WebFragment delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);

        return Router.getInstance().handlerWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {

        super.onPageStarted(view, url, favicon);
        if (iPageLoadListener != null) {
            iPageLoadListener.onLoadStart();
        }

        LatteLoader.showLoading(view.getContext());
    }

    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
         注意，这里的cookie和API请求是不一样的，这个在网页中不可见
         */
        final String webHost = Latte.getConfiguration(ConfigType.WEB_HOST);
        if (webHost != null && manager.hasCookies()) {

            final String cookieStr = manager.getCookie(webHost);
            if (cookieStr != null && !cookieStr.equals("")) {

                LattePreference.addCustomAppProfile("cookie",cookieStr);
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (iPageLoadListener != null) {
            iPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }
}
