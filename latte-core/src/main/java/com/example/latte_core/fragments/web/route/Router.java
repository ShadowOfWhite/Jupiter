package com.example.latte_core.fragments.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.fragments.web.WebFragment;
import com.example.latte_core.fragments.web.WebFragmentImpl;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class Router {

    private Router() {

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handlerWebUrl(WebFragment delegate, String url) {

        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(),url);
            return true;
        }

        final LatteFragment topDelegate = delegate.getTopDelegate();


        WebFragmentImpl webDelegate = WebFragmentImpl.create(url);

        topDelegate.start(webDelegate);

        return true;
    }

    private void loadWebPage(WebView webView,String url){
        if (webView != null){
            Log.i("test", "loadWebPage: 开始加载网页");
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("webView is null");
        }
    }

    public void loadPage(WebFragment delegate, String url){
        loadWebPage(delegate.getWebView(),url);
    }

    private void loadLocalPage(WebView webView,String url){
        loadWebPage(webView,"file:///android_asset/"+url);

    }

    private void loadPage(WebView webView,String url){
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else {
            loadLocalPage(webView,url);
        }
    }

    private void callPhone(Context context, String uri) {

        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
//        context.startActivity(intent);
        ContextCompat.startActivity(context, intent, null);
    }

}
