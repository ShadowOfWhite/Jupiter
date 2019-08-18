package com.example.asus.startproject;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.latte_core.fragments.web.IUrlHandler;
import com.example.latte_core.fragments.web.WebFragment;
import com.example.latte_core.fragments.web.route.Router;

/**
 * 作者：贪欢
 * 时间：2019/8/17
 * 描述：
 */
public class UrlHandlerExample implements IUrlHandler {
    @Override
    public void handleUrl(WebFragment fragment) {
        //是我们跳转新页面的URL
        final String url = fragment.getUrl();
        if (url != null){
            LogUtils.i(url);
            //用原生的方式代替浏览器的跳转，每次打开新的WebFragment
            Router.getInstance().loadPage(fragment,url);


        }
    }
}
