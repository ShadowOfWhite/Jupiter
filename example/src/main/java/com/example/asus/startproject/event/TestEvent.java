package com.example.asus.startproject.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.example.latte_core.fragments.web.event.Event;

/**
 * 作者：贪欢
 * 时间：2019/6/27
 * 描述：
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),"测试event："+params,Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")){
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
